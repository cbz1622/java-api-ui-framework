package org.postoffice.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchTextBox;

    @FindBy(id = "nav-search-submit-text")
    private WebElement searchSubmitButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String query) {
        waitUntilPageLoads();
        waitForVisibility(searchTextBox);
        searchTextBox.sendKeys(query);
        if (isElementDisplayed(searchSubmitButton)) {
            searchSubmitButton.click();
        }
    }
}
