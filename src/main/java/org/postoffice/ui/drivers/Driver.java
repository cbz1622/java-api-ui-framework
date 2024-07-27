package org.postoffice.ui.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.postoffice.ui.config.Config;

public class Driver {
    private static ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

    public Driver() {
    }

    public static WebDriver getDriver() {
        if (webdriver.get() == null) {
            intializeDriver();
            webdriver.get().manage().window().maximize();
        }
        return webdriver.get();
    }

    public static void quitDriver() {
        if (webdriver.get() != null) {
            webdriver.get().quit();
            webdriver.remove();
        }
    }

    private static void intializeDriver() {
        String browser = Config.Input.BROWSER.getValue();
        boolean isHeadless = Boolean.parseBoolean(Config.Input.HEADLESS.getValue());

        if (browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOptions= new ChromeOptions();
            if (isHeadless) {
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
            }
            webdriver.set(new ChromeDriver(chromeOptions));
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
