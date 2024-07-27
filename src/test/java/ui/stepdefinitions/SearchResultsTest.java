package ui.stepdefinitions;

import static org.junit.Assert.assertTrue;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.postoffice.ui.config.Config;
import org.postoffice.ui.drivers.Driver;
import org.postoffice.ui.pages.HomePage;
import org.postoffice.ui.pages.SearchResultsPage;

public class SearchResultsTest extends BaseTest {
    private static HomePage homePage;

    @Before
    public void setup() {
        driver = getDriver();
        homePage = new HomePage(driver);
    }

    @After
    public void tearDown() {
        Driver.quitDriver();
    }

    @Given("User access the Home page")
    public void user_access_the_home_page() {
        driver.get(Config.Input.HOME_URL.getValue());
    }

    @When("User enters query string as {string}")
    public void user_enters_query_string_as(String queryString) {
        homePage.searchFor(queryString);
    }

    @Then("the product list page should include the appropriate result {string}")
    public void the_product_list_page_should_include_the_appropriate_result(String result) {
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        assertTrue(searchResultsPage.isTextDisplayedOnItem(0, result));
    }
}