package org.postoffice.ui.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "(//div[contains(@class, 'puisg-row')])")
    private List<WebElement> searchResults;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTextDisplayedOnItem(int index, String text) {
        waitUntilPageLoads();
        if (returnResultByIndex(searchResults, index) != null) {
            return textContains(
                    returnResultByIndex(searchResults, index), text);
        }
        return textContains(getPageText(),text);
    }
}