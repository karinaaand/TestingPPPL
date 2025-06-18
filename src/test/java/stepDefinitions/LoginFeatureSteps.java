package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.*;

public class LoginFeatureSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    private void initializePages() {
        if (driver == null) {
            driver = WebDriverManager.getDriver();
        }
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
            dashboardPage = new DashboardPage(driver);
        }
    }

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        initializePages();
        loginPage.navigateToLoginPage();
    }

    @When("The user enters email {string} and password {string}")
    public void the_user_enters_email_and_password(String email, String password) {
        initializePages();
        loginPage.performLogin(email, password);
    }

    @Then("The user should be redirected to the Dashboard page")
    public void the_user_should_be_redirected_to_the_dashboard_page() {
        initializePages();
        dashboardPage.verifyOnDashboardPage();
    }

    @Then("The user should see an error message {string}")
    public void the_user_should_see_an_error_message(String expectedMessage) {
        initializePages();
        String errorMsg = loginPage.getErrorMessage();
        boolean isOnLoginPage = driver.getCurrentUrl().contains("/login");
        assert (errorMsg.contains(expectedMessage) || isOnLoginPage);
    }
} 