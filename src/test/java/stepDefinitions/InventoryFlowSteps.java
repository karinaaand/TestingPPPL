package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;
import pages.*;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class InventoryFlowSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;
    private HistoryPage historyPage;
    private DashboardPage dashboardPage;

    private void initializePages() {
        if (driver == null) {
            driver = WebDriverManager.getDriver();
            wait = WebDriverManager.getWait();
        }
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
            inventoryPage = new InventoryPage(driver);
            checkoutPage = new CheckoutPage(driver);
            historyPage = new HistoryPage(driver);
            dashboardPage = new DashboardPage(driver);
        }
    }

    @Given("I am logged in as admin")
    public void iAmLoggedInAsAdmin() {
        initializePages();
        driver.get("https://simbat.madanateknologi.web.id/login");
        loginPage.enterEmail("admin@simbat.disyfa.site");
        loginPage.enterPassword("admin");
        loginPage.clickLoginButton();
        Assert.assertTrue("Dashboard should be displayed after login", dashboardPage.isDashboardDisplayed());
    }

    @When("I navigate to the inventory page")
    public void iNavigateToTheInventoryPage() {
        initializePages();
        inventoryPage = new InventoryPage(driver);
        inventoryPage.navigateToInventory();
    }

    @And("I add new stock with the following details")
    public void iAddNewStockWithTheFollowingDetails(DataTable dataTable) {
        initializePages();
        List<Map<String, String>> stockDetails = dataTable.asMaps();
        Map<String, String> data = stockDetails.get(0);

        try {
            inventoryPage.clickTambahButton();
            Thread.sleep(1000);
            inventoryPage.fillInventoryForm(
                    data.get("medicineName"),
                    data.get("quantity"),
                    data.get("expiryDate")
            );
            Thread.sleep(1000);
            inventoryPage.addDrugItem();
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to add stock", e);
        }
    }

    @And("I save the inventory")
    public void iSaveTheInventory() {
        try {
            inventoryPage.saveInventory();
            Thread.sleep(1000);
            checkoutPage.navigateToCheckout();
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to save inventory", e);
        }
    }

    @Then("I should be on the checkout page")
    public void iShouldBeOnTheCheckoutPage() {
        Assert.assertTrue("Should be on checkout page",
                driver.getCurrentUrl().contains("/transaction/create"));
    }

    @And("I add item {string} with quantity {string}")
    public void iAddItemWithQuantity(String medicineName, String quantity) {
        try {
            checkoutPage.addItemWithQuantity(medicineName, quantity);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to add item with quantity", e);
        }
    }

    @And("I select checkout type {string}")
    public void iSelectCheckoutType(String type) {
        try {
            checkoutPage.selectCheckoutType(type);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to select checkout type", e);
        }
    }

    @And("I proceed with checkout")
    public void iProceedWithCheckout() {
        try {
            checkoutPage.proceedWithCheckout();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to proceed with checkout", e);
        }
    }

    @When("I navigate to the history page")
    public void iNavigateToTheHistoryPage() {
        try {
            checkoutPage.navigateToCheckout();
            wait.until(ExpectedConditions.urlContains("/transaction/create"));
            Thread.sleep(1000);
            historyPage.navigateToHistory();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to navigate to history page", e);
        }
    }

    @When("I logout from the system")
    public void iLogoutFromTheSystem() {
        try {
            dashboardPage.clickLogoutButton();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to logout", e);
        }
    }

    @Then("I should be logged out successfully")
    public void iShouldBeLoggedOutSuccessfully() {
        Assert.assertTrue("Should be redirected to login page",
                driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue("Login page should be displayed", loginPage.isLoginPageDisplayed());
    }
}
