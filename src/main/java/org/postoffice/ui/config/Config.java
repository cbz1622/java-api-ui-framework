package org.postoffice.ui.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String CONFIG_FILE = "src/main/resources/ui/config.properties";
    private static final Properties properties;
    static {
        properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(CONFIG_FILE);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Input {
        BROWSER("required.browser"),
        HOME_URL("home.url"),
        OBJECTS_WAIT_TIME_IN_SECS("objects.wait.time.in.secs"),
        HEADLESS ("headless");

        private final String property;

        Input(String property) {
            this.property = property;
        }

        public String getValue() {
            return properties.getProperty(property);
        }
    }
}
