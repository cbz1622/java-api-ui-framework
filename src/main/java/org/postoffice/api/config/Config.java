package org.postoffice.api.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String CONFIG_FILE = "src/main/resources/api/config.properties";
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
        BASE_URI("base.uri"),
        HEADER_CONTENT_TYPE("header.contentType"),
        HEADER_ACCEPT("header.accept"),
        ENDPOINT("endpoint"),
        CONNECTION_TIMEOUT("connection.timeout");

        private final String property;

        Input(String property) {
            this.property = property;
        }

        public String getValue() {
            return properties.getProperty(property);
        }
    }
}

