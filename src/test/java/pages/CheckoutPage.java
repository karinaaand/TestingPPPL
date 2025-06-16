package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    private WebDriverWait wait;

    private By medicineNameInput = By.xpath("//input[@placeholder='Nama Item']");
    private By quantityInput = By.xpath("//input[@placeholder='Jumlah']");
    private By tambahButton = By.xpath("//button[contains(., 'Tambah')]");
    private By checkoutButton = By.xpath("//button[contains(., 'Checkout')]");
    private By simpanButton = By.xpath("//div[@id='addModal']//button[@onclick='submitForm()']");
    private By drugSuggestionOption = By.xpath("//div[contains(text(), 'Paracetamol 500mg') and contains(text(), '1 pcs')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToCheckout() {
        try {
            By cartIcon = By.xpath("//a[contains(@href, '/transaction/create')]");
            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
            cartButton.click();
            wait.until(ExpectedConditions.urlContains("/transaction/create"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to checkout page", e);
        }
    }

    public void addItemWithQuantity(String medicineName, String quantity) {
        try {
            WebElement medicineInput = wait.until(ExpectedConditions.elementToBeClickable(medicineNameInput));
            medicineInput.clear();
            Thread.sleep(500);
            medicineInput.sendKeys(medicineName);
            Thread.sleep(2000);

            try {
                WebElement suggestionOption = wait.until(ExpectedConditions.elementToBeClickable(drugSuggestionOption));
                Thread.sleep(1000);
                suggestionOption.click();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Dropdown not found with primary locator, trying alternatives...");
                By alternativeOption = By.xpath("//*[contains(text(), 'Paracetamol 500mg') and contains(text(), '1 pcs')]");
                WebElement altSuggestion = wait.until(ExpectedConditions.elementToBeClickable(alternativeOption));
                altSuggestion.click();
                Thread.sleep(1000);
            }

            WebElement quantityElement = wait.until(ExpectedConditions.elementToBeClickable(quantityInput));
            quantityElement.clear();
            Thread.sleep(500);
            quantityElement.sendKeys(quantity);
            Thread.sleep(1000);
            Thread.sleep(500);
            WebElement tambahBtn = wait.until(ExpectedConditions.elementToBeClickable(tambahButton));
            tambahBtn.click();
            Thread.sleep(500);

        } catch (Exception e) {
            throw new RuntimeException("Failed to add item with quantity", e);
        }
    }

    public void proceedWithCheckout() {
        try {
            Thread.sleep(1000);
            WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
            Thread.sleep(1000);
            checkoutBtn.click();
            Thread.sleep(1000);
            WebElement simpanBtn = wait.until(ExpectedConditions.elementToBeClickable(simpanButton));
            Thread.sleep(1000);
            simpanBtn.click();
            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Failed to proceed with checkout", e);
        }
    }

    public void selectCheckoutType(String type) {
        try {
            if ("Clinic".equalsIgnoreCase(type)) {
                By klinikTab = By.xpath("//div[@id='klinikTab']");
                WebElement klinikElement = wait.until(ExpectedConditions.elementToBeClickable(klinikTab));
                klinikElement.click();
            } else if ("Inventory".equalsIgnoreCase(type)) {
                By inventoryTab = By.xpath("//div[@id='inventoryTab']");
                WebElement inventoryElement = wait.until(ExpectedConditions.elementToBeClickable(inventoryTab));
                inventoryElement.click();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to select checkout type: " + type, e);
        }
    }

    public void setDiscount(String percent) {
        driver.findElement(By.id("discount")).clear();
        driver.findElement(By.id("discount")).sendKeys(percent);
    }

    public boolean isInvoiceDisplayed() {
        return driver.getPageSource().contains("Invoice") || driver.getPageSource().contains("Success");
    }
}
