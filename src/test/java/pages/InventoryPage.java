package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inventoryButton = By.xpath("//button[contains(., 'Inventory')]");
    private By inventoryDropdown = By.id("dropdown-inventory");
    private By barangMasukLink = By.xpath("//a[contains(@href, '/inventory/inflows')]");
    private By tambahButton = By.xpath("//a[contains(@href, '/inventory/inflows/create')]");

    private By vendorDropdown = By.xpath("//select[@name='vendor_id']");
    private By medicineNameInput = By.xpath("//input[@name='drug']");
    private By quantityInput = By.xpath("//input[@name='quantity']");
    private By unitSelect = By.xpath("//select[@name='unit']");
    private By paymentMethodDropdown = By.xpath("//select[@name='method']");
    private By dueDateInput = By.xpath("//input[@name='due']");
    private By expiryDateInput = By.xpath("//input[@name='expired']");
    private By addDrugButton = By.xpath("//button[contains(., 'Tambah') and not(contains(., '+'))]");
    private By inventoryModalButton = By.xpath("//div[@id='destinationModal']//button[contains(@onclick, 'selectDestination') and contains(., 'Inventory')]");
    private By simpanButton = By.xpath("//button[contains(@onclick, 'customBuatModal') and contains(., 'Simpan')]");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToInventory() {
        try {
            WebElement inventoryBtn = wait.until(ExpectedConditions.elementToBeClickable(inventoryButton));
            inventoryBtn.click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryDropdown));
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(barangMasukLink));
            WebElement barangMasukBtn = wait.until(ExpectedConditions.elementToBeClickable(barangMasukLink));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", barangMasukBtn);
            Thread.sleep(1000);
            barangMasukBtn.click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.urlContains("/inventory/inflows"));

        } catch (InterruptedException e) {
            throw new RuntimeException("Navigation interrupted", e);
        }
    }

    public void clickTambahButton() {
        try {
            WebElement tambahLink = wait.until(ExpectedConditions.presenceOfElementLocated(tambahButton));
            wait.until(ExpectedConditions.visibilityOf(tambahLink));
            Thread.sleep(1000);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tambahLink);
            Thread.sleep(1000);
            tambahLink.click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.urlContains("/inventory/inflows/create"));
            wait.until(ExpectedConditions.presenceOfElementLocated(vendorDropdown));
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to click Tambah button", e);
        }
    }

    public void fillInventoryForm(String medicineName, String quantity, String expiryDate) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(vendorDropdown));
            Thread.sleep(500);
            Select vendorSelect = new Select(driver.findElement(vendorDropdown));
            vendorSelect.selectByIndex(1);
            Thread.sleep(500);
            Select paymentSelect = new Select(driver.findElement(paymentMethodDropdown));
            paymentSelect.selectByValue("cash");
            Thread.sleep(500);
            WebElement medicineInput = driver.findElement(medicineNameInput);
            medicineInput.clear();
            Thread.sleep(500);
            medicineInput.sendKeys(medicineName);
            Thread.sleep(500);
            WebElement quantityElement = driver.findElement(quantityInput);
            quantityElement.clear();
            Thread.sleep(500);
            quantityElement.sendKeys(quantity);
            Thread.sleep(500);
            WebElement expiryInput = driver.findElement(expiryDateInput);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", expiryInput, expiryDate);
            Thread.sleep(500);

        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to fill inventory form", e);
        }
    }

    public void addDrugItem() {
        try {
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addDrugButton));
            addBtn.click();
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to add drug item", e);
        }
    }

    public void clickSimpanButton() {
        try {
            WebElement simpanBtn = wait.until(ExpectedConditions.elementToBeClickable(simpanButton));
            Thread.sleep(1000);
            simpanBtn.click();
            Thread.sleep(1000);
            WebElement inventoryBtn = wait.until(ExpectedConditions.elementToBeClickable(inventoryModalButton));
            inventoryBtn.click();
            Thread.sleep(1000);
            By finalSaveLocator = By.xpath("//div[@id='addModal']//button[@onclick='submitForm()']");
            WebElement finalSaveBtn = wait.until(ExpectedConditions.elementToBeClickable(finalSaveLocator));
            finalSaveBtn.click();
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to click Simpan button", e);
        }
    }

    public void saveInventory() {
        try {
            clickSimpanButton();
            wait.until(ExpectedConditions.urlContains("/inventory/inflows"));
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to save inventory", e);
        }
    }
}