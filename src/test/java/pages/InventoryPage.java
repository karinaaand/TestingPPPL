package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToInventory() {
        driver.findElement(By.linkText("Inventory")).click();
    }

    public void addNewStock(String vendor, String medicineName, String paymentMethod, String dueDate,
                            String quantity, String unit, String price, String expiryDate) {
        selectVendor(vendor);
        selectPaymentMethod(paymentMethod);
        setDueDate(dueDate);
        setMedicineName(medicineName);
        setQuantity(quantity);
        selectUnit(unit);
        setPrice(price);
        setExpiryDate(expiryDate);
    }

    public void selectVendor(String vendor) {
        WebElement vendorDropdown = driver.findElement(By.cssSelector("select[name='vendor_id']"));
        vendorDropdown.click();
        vendorDropdown.findElement(By.xpath(".//option[contains(text(), '" + vendor + "')]")).click();
    }

    public void selectPaymentMethod(String method) {
        WebElement methodDropdown = driver.findElement(By.cssSelector("select[name='method']"));
        methodDropdown.click();
        methodDropdown.findElement(By.xpath(".//option[contains(text(), '" + method + "')]")).click();
    }

    public void setDueDate(String date) {
        driver.findElement(By.cssSelector("input[name='due']")).sendKeys(date);
    }

    public void setMedicineName(String name) {
        driver.findElement(By.cssSelector("input[name='drug']")).sendKeys(name);
    }

    public void setQuantity(String quantity) {
        driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys(quantity);
    }

    public void selectUnit(String unit) {
        WebElement unitDropdown = driver.findElement(By.cssSelector("select[name='unit']"));
        unitDropdown.click();
        unitDropdown.findElement(By.xpath(".//option[contains(text(), '" + unit + "')]")).click();
    }

    public void setPrice(String price) {
        driver.findElement(By.cssSelector("input[name='price']")).sendKeys(price);
    }

    public void setExpiryDate(String date) {
        driver.findElement(By.cssSelector("input[name='expired']")).sendKeys(date);
    }

    public void clickAddButton() {
        driver.findElement(By.xpath("//button[contains(text(),'Tambah')]")).click();
    }

    public void clickSaveButton() {
        driver.findElement(By.xpath("//button[contains(text(),'Simpan')]")).click();
    }

    public void confirmDestination(String destination) {
        WebElement modal = driver.findElement(By.id("destinationModal"));
        if (destination.equalsIgnoreCase("warehouse")) {
            modal.findElement(By.xpath(".//button[contains(text(),'Inventory')]")).click();
        } else {
            modal.findElement(By.xpath(".//button[contains(text(),'Klinik')]")).click();
        }
    }

    public boolean isMedicineVisible(String medicineName) {
        return driver.getPageSource().contains(medicineName);
    }

    public void clickUploadButton() {
        driver.findElement(By.xpath("//button[contains(text(),'Upload')]")).click();
    }

    public void uploadFile(String filePath) {
        WebElement fileInput = driver.findElement(By.id("dropzone-file"));
        fileInput.sendKeys(filePath);
        driver.findElement(By.xpath("//button[contains(text(),'Tambah')]")).click();
    }

    public void downloadTemplate() {
        driver.findElement(By.xpath("//a[contains(text(),'Template')]")).click();
    }
}