package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigProperties — reads key-value settings from config.properties.
 *
 * Mirrors the ATI framework's ConfigProperties utility. Supports fallback
 * default values so scripts won't crash on missing optional settings.
 * The config file path defaults to "config/config.properties" but can be
 * overridden with the system property -Dconfig.file=path/to/config.properties.
 *
 * @author  Portfolio Framework
 * @version 1.0
 */
public class ConfigProperties {

    private static Properties properties;
    private static final String DEFAULT_CONFIG_PATH = "config/config.properties";

    static {
        loadProperties();
    }

    private ConfigProperties() {}

    /**
     * Loads properties from the config file at startup.
     */
    private static void loadProperties() {
        properties = new Properties();
        String configPath = System.getProperty("config.file", DEFAULT_CONFIG_PATH);

        try (InputStream input = new FileInputStream(configPath)) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("[ConfigProperties] WARNING: Could not load config file at: " + configPath);
            System.err.println("[ConfigProperties] Using system properties and defaults only.");
        }
    }

    /**
     * Returns the value for the given key from config.properties.
     * System properties (-D flags) take precedence over the config file.
     *
     * @param key The property key
     * @return String value, or null if not found
     */
    public static String getValue(String key) {
        // System property (-Dkey=value) overrides config file
        String systemVal = System.getProperty(key);
        if (systemVal != null) return systemVal;
        return properties.getProperty(key);
    }

    /**
     * Returns the value for the given key, falling back to a default if missing.
     *
     * @param key          The property key
     * @param defaultValue Fallback value if key is not found
     * @return String value from config, or the defaultValue
     */
    public static String getValue(String key, String defaultValue) {
        String val = getValue(key);
        return (val != null && !val.trim().isEmpty()) ? val : defaultValue;
    }

    /**
     * Returns the value as a boolean. Defaults to false if key is missing.
     *
     * @param key The property key
     * @return boolean value
     */
    public static boolean getBooleanValue(String key) {
        return Boolean.parseBoolean(getValue(key, "false"));
    }
}
