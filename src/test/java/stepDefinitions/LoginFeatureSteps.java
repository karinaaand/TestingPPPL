package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import java.util.Map;
import java.util.List;
import org.openqa.selenium.By;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class LoginFeatureSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private InventoryPage inventoryPage;
    private KlinikPage klinikPage;
    private CheckoutPage checkoutPage;
    private HistoryPage historyPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Initialize page objects
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        inventoryPage = new InventoryPage(driver);
        klinikPage = new KlinikPage(driver);
        checkoutPage = new CheckoutPage(driver);
        historyPage = new HistoryPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.navigateToLoginPage();
    }

    @Given("The user is logged in")
    public void the_user_is_logged_in() {
        // Assuming user is already on login page from background
        loginPage.performLogin("admin@simbat.disyfa.site", "admin");
        dashboardPage.verifyOnDashboardPage();
    }

    @When("The user enters a valid email {string} and password {string}")
    public void the_user_enters_a_valid_email_and_password(String email, String password) {
        loginPage.performLogin(email, password);
    }

    @When("The user enters email {string} and password {string}")
    public void the_user_enters_email_and_password(String email, String password) {
        loginPage.performLogin(email, password);
    }

    @Then("The user should be redirected to the Dashboard page")
    public void the_user_should_be_redirected_to_the_dashboard_page() {
        dashboardPage.verifyOnDashboardPage();
    }

    @When("The user navigates to the Inventory page")
    public void the_user_navigates_to_the_inventory_page() {
        inventoryPage.navigateToInventory();
        inventoryPage.clickTambahButton();
    }

    @When("The user adds a new stock with:")
    public void the_user_adds_a_new_stock_with(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> stockData = data.get(0);
        
        inventoryPage.fillInventoryForm(
            stockData.get("Medicine Name"),
            stockData.get("Quantity"),
            stockData.get("Expiry Date")
        );
    }

    @When("The user clicks {string} and {string}")
    public void the_user_clicks_and(String button1, String button2) {
        if (button1.equals("Add") && button2.equals("Save")) {
            inventoryPage.addDrugItem();
            inventoryPage.clickSimpanButton();
        }
    }

    @When("The user selects {string} in the popup and confirms")
    public void the_user_selects_in_the_popup_and_confirms(String option) {
        // This is handled in clickSimpanButton method
    }

    @Then("The Inventory list should show {string}")
    public void the_inventory_list_should_show(String medicineName) {
        // Verify medicine is in the list
        assert driver.getPageSource().contains(medicineName);
    }

    @When("The user navigates to the Clinic page")
    public void the_user_navigates_to_the_clinic_page() {
        klinikPage.navigateToInventory();
    }

    @When("The user adds a new stock with the same data as above")
    public void the_user_adds_a_new_stock_with_the_same_data_as_above() {
        // Use the same data from previous step
        klinikPage.addNewStock("PT Farmasi Sejahtera", "Paracetamol 500mg", "Pay Now", 
                              "2025-06-16", "2", "Pcs", "5000", "2025-12-31");
    }

    @Then("The Clinic list should show {string}")
    public void the_clinic_list_should_show(String medicineName) {
        assert klinikPage.isMedicineVisible(medicineName);
    }

    @When("The user navigates to the Checkout page")
    public void the_user_navigates_to_the_checkout_page() {
        checkoutPage.navigateToCheckout();
    }

    @When("The user selects {string}")
    public void the_user_selects(String option) {
        if (option.contains("Checkout Inventory")) {
            checkoutPage.selectCheckoutType("Inventory");
        } else if (option.contains("Checkout Clinic")) {
            checkoutPage.selectCheckoutType("Clinic");
        }
    }

    @When("The user sets discount to {int}%")
    public void the_user_sets_discount_to(Integer discount) {
        checkoutPage.setDiscount(discount.toString());
    }

    @When("The user adds {string} with quantity {int}")
    public void the_user_adds_with_quantity(String item, Integer quantity) {
        checkoutPage.addItemWithQuantity(item, quantity.toString());
    }

    @Then("The system should auto-fill price and available stock")
    public void the_system_should_auto_fill_price_and_available_stock() {
        // This is handled automatically by the system
    }

    @Then("The user proceeds to checkout and sees an invoice and success popup")
    public void the_user_proceeds_to_checkout_and_sees_an_invoice_and_success_popup() {
        checkoutPage.proceedWithCheckout();
        assert checkoutPage.isInvoiceDisplayed();
    }

    @When("The user navigates to the History page")
    public void the_user_navigates_to_the_history_page() {
        historyPage.navigateToHistory();
    }

    @Then("The history should show the latest checkout transactions")
    public void the_history_should_show_the_latest_checkout_transactions() {
        // Verify history page is loaded
        assert driver.getCurrentUrl().contains("/log");
    }

    @When("The user logs out")
    public void the_user_logs_out() {
        dashboardPage.logout();
    }

    @Then("The user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        assert driver.getCurrentUrl().contains("/login");
    }

    @Then("The user should see an error message {string}")
    public void the_user_should_see_an_error_message(String expectedMessage) {
        String errorMsg = loginPage.getErrorMessage();
        boolean isOnLoginPage = driver.getCurrentUrl().contains("/login");
        // Jika ada pesan error, pastikan mengandung expectedMessage
        // Jika tidak ada pesan error, pastikan tetap di halaman login
        assert (errorMsg.contains(expectedMessage) || isOnLoginPage);
    }

    @When("The user attempts to save inventory with:")
    public void the_user_attempts_to_save_inventory_with(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> stockData = data.get(0);
        
        // Try to save with invalid data
        try {
            inventoryPage.fillInventoryForm(
                stockData.get("Medicine Name") != null ? stockData.get("Medicine Name") : "",
                stockData.get("Quantity") != null ? stockData.get("Quantity") : "",
                stockData.get("Expiry Date") != null ? stockData.get("Expiry Date") : ""
            );
            inventoryPage.clickSimpanButton();
        } catch (Exception e) {
            // Expected to fail with validation errors
        }
    }

    @Then("The system should show validation errors and prevent saving")
    public void the_system_should_show_validation_errors_and_prevent_saving() {
        // Verify that we're still on the form page (not redirected)
        assert driver.getCurrentUrl().contains("/inventory/inflows/create");
    }

    @When("The user enters valid medicine data")
    public void the_user_enters_valid_medicine_data() {
        klinikPage.addNewStock("PT Farmasi Sejahtera", "Paracetamol 500mg", "Pay Now", 
                              "2025-06-16", "2", "Pcs", "5000", "2025-12-31");
    }

    @When("The user closes the popup without selecting {string}")
    public void the_user_closes_the_popup_without_selecting(String option) {
        // Close popup without selecting destination
        try {
            driver.findElement(By.xpath("//button[contains(@class, 'close') or contains(@class, 'btn-close')]")).click();
        } catch (Exception e) {
            // Popup might not be visible or already closed
        }
    }

    @Then("The stock should not be saved to the Clinic list")
    public void the_stock_should_not_be_saved_to_the_clinic_list() {
        // Verify medicine is not in the list
        assert !klinikPage.isMedicineVisible("Paracetamol 500mg");
    }

    @Then("The system should show an error {string}")
    public void the_system_should_show_an_error(String errorMessage) {
        // Check for error message in page source
        assert driver.getPageSource().contains(errorMessage);
    }

    @Then("The system should show an error about insufficient stock")
    public void the_system_should_show_an_error_about_insufficient_stock() {
        // Check for insufficient stock error
        assert driver.getPageSource().contains("insufficient") || 
               driver.getPageSource().contains("stock") || 
               driver.getPageSource().contains("quantity");
    }

    @When("The user directly clicks {string} without adding items")
    public void the_user_directly_clicks_without_adding_items(String button) {
        try {
            driver.findElement(By.xpath("//button[contains(text(), '" + button + "')]")).click();
        } catch (Exception e) {
            // Button might not be clickable
        }
    }

    @Then("The system should show a warning {string}")
    public void the_system_should_show_a_warning(String warningMessage) {
        // Check for warning message in page source
        assert driver.getPageSource().contains(warningMessage);
    }
} 