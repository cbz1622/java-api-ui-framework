package ui.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.postoffice.ui.drivers.Driver;

public abstract class BaseTest {
    protected WebDriver driver;

    public BaseTest() {
        this.driver = Driver.getDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }
}

