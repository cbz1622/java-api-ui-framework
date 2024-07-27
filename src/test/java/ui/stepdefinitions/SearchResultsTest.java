package ui.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Assertions;
import org.postoffice.ui.config.Config;
import org.postoffice.ui.drivers.Driver;
import org.postoffice.ui.pages.HomePage;
import org.postoffice.ui.pages.SearchResultsPage;

public class SearchResultsTest extends BaseTest {
    private static HomePage homePage;
    private static final Logger logger = (Logger) LogManager.getLogger(SearchResultsTest.class);

    @Before
    public void setup() {
        logger.info("Opening browser..");
        driver = getDriver();
        homePage = new HomePage(driver);
    }

    @After
    public void tearDown() {
        Driver.quitDriver();
    }

    @Given("User access the Home page")
    public void user_access_the_home_page() {
        logger.info("Access home page of " + Config.Input.HOME_URL.getValue());
        driver.get(Config.Input.HOME_URL.getValue());
    }

    @When("User enters query string as {string}")
    public void user_enters_query_string_as(String queryString) {
        logger.info("Search string is entered as " + queryString);
        homePage.searchFor(queryString);
    }

    @Then("the product list page should include the appropriate result {string}")
    public void the_product_list_page_should_include_the_appropriate_result(String result) {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        logger.info("Validating results..");
        Assertions.assertTrue(searchResultsPage.isTextDisplayedOnItem(0, result));
    }
}