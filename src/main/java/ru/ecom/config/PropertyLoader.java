package ru.ecom.config;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private final Properties applicationProperties = new Properties();
    private static PropertyLoader INSTANCE = null;

    private PropertyLoader() {
        try {
            applicationProperties.load(PropertyLoader.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropertyLoader();
        }
        return INSTANCE;
    }

    public String getProperty(String key){
        return applicationProperties.getProperty(key);
    }
}