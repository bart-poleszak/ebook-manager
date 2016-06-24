package com.example.bp.ebookmanager.config;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public class ConfigManager {
    private static Configuration configuration = null;

    public static Configuration get() {
        return configuration;
    }

    public static void set(Configuration configuration) {
        if (ConfigManager.configuration == null)
            ConfigManager.configuration = configuration;
        else
            throw new RuntimeException("Can't override configuration");
    }
}