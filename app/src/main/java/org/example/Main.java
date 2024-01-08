package org.example;

import io.micrometer.common.util.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Main {
    public static void main(String[] args) {
         initializeAppConfigurations();
         SpringApplication.run(Main.class, args);

    }

    private static String viditureHome = System.getenv("SERVER_HOME");

    public static String APP_NAME = System.getenv("APP_NAME");
    private static String appProfile = System.getenv("APP_PROFILE");
    private static String configProvider = System.getenv("APP_CONFIG_PROVIDER");


    public static void initializeAppConfigurations() {
//        String appName = System.getenv("APP_NAME");
//        if (StringUtils.isNotBlank(appName)) {
//            APP_NAME = appName;
//        }
////        log.info("initializing application configurations. VIDITURE_HOME={}, APP_NAME={}, APP_PROFILE = {}, CONTAINER_ROLE = {}", viditureHome, APP_NAME, appProfile, containerRole);

        String configFileName = APP_NAME + "-" + appProfile + ".yaml";
//        if (configProvider != null && (configProvider.equals("s3"))) {
//            try {
//                fetchFilesFromS3(configFileName, configFileName);
//            } catch (IOException e) {
//                log.error("error while fetching config s3 files");
//                e.printStackTrace();
//            }
//        }

        if (StringUtils.isEmpty(viditureHome) || StringUtils.isEmpty(APP_NAME) || StringUtils.isEmpty(appProfile)) {
           // log.error("env variables not found. VIDITURE_HOME={}, APP_NAME={}, APP_PROFILE={}", viditureHome, APP_NAME, appProfile);
            throw new RuntimeException("required env variable not found. set VIDITURE_HOME, APP_NAME, APP_PROFILE");
        }
        String profileLocation = viditureHome + "/config/" + configFileName;
       // log.info("application configuration location = {}", profileLocation);
        System.setProperty("spring.config.location", profileLocation);
    }
}

