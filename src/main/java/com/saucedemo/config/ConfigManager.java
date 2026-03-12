package com.saucedemo.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigManager — singleton that loads config.properties.
 * Priority: System Property → config.properties → hardcoded default
 */
public class ConfigManager {

    private static ConfigManager instance;
    private final Properties props = new Properties();

    private ConfigManager() {
        try (FileInputStream fis = new FileInputStream(
                "src/test/resources/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("[ConfigManager] config.properties not found – using defaults.");
        }
    }

    public static ConfigManager getInstance() {
        if (instance == null) instance = new ConfigManager();
        return instance;
    }

    /** System property beats config file beats hardcoded default. */
    public String get(String key, String defaultValue) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isEmpty()) return sys;
        return props.getProperty(key, defaultValue);
    }

    public String  getBaseUrl()  { return get("baseUrl",  "https://www.saucedemo.com"); }
    public String  getBrowser()  { return get("browser",  "chrome"); }
    public boolean isHeadless()  { return Boolean.parseBoolean(get("headless", "false")); }
    public int     getTimeout()  { return Integer.parseInt(get("timeout", "10")); }
    public String  getReportDir(){ return get("reportDir", "reports"); }
}
