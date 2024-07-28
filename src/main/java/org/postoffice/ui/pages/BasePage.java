package org.postoffice.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.postoffice.ui.config.Config;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(Long.parseLong(Config.Input.OBJECTS_WAIT_TIME_IN_SECS.getValue())));
        PageFactory.initElements(driver, this);
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isTextDisplayed(WebElement element, String text) {
        try {
            return element.getText().equals(text);
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean textContains(WebElement element, String partialText) {
        try {
            return normalizeText(element.getText()).contains(partialText);
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean textContains(String sourceText, String partialText) {
        try {
            return normalizeText(sourceText).contains(partialText);
        } catch (Exception e) {
            return false;
        }
    }

    private String normalizeText(String text) {
        if (text == null) {
            return "";
        }
        return text.replaceAll("\\s+", " ").trim();
    }

    protected WebElement returnResultByIndex(List<WebElement> webElements , int index) {
        if ((index >=0) && (index < webElements.size())) {
            return webElements.get(index);
        }
        return null;
    }

    protected void waitUntilPageLoads() {
            wait.until((ExpectedCondition<Boolean>) driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    protected String getPageText() {
        WebElement bodyElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        return bodyElement.getText();
    }
}

