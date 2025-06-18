package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        loginPage.navigateToLoginPage();
    }

    @When("User enters email {string} and password {string}")
    public void user_enters_email_and_password(String email, String password) {
        loginPage.performLogin(email, password);
    }

    @Then("User should see the Dashboard page")
    public void user_should_see_the_dashboard_page() {
        dashboardPage.verifyOnDashboardPage();
    }

    @When("User performs the logout process")
    public void user_performs_the_logout_process() {
        dashboardPage.logout();
    }

    @Then("User should be returned to the login page")
    public void user_should_be_returned_to_the_login_page() {
        // sederhana: periksa URL berisi /login
        assert driver.getCurrentUrl().contains("/login");
        driver.quit();
    }

    @Then("User should see an error message {string}")
    public void user_should_see_an_error_message(String expected) {
        assert loginPage.getErrorMessage().contains(expected);
//        driver.quit();
    }
}
