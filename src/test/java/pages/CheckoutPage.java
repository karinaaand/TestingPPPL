package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        driver.findElement(By.linkText("Checkout")).click();
    }

    public void selectCheckoutType(String type) {
        driver.findElement(By.xpath("//button[contains(text(),'" + type + "')]")).click();
    }

    public void setDiscount(String percent) {
        driver.findElement(By.id("discount")).clear();
        driver.findElement(By.id("discount")).sendKeys(percent);
    }

    public void addItem(String itemName, String quantity) {
        driver.findElement(By.id("item_search")).sendKeys(itemName);
        driver.findElement(By.id("quantity")).sendKeys(quantity);
        driver.findElement(By.id("btn_add_item")).click();
    }

    public void proceedToCheckout() {
        driver.findElement(By.id("btn_checkout")).click();
    }

    public boolean isInvoiceDisplayed() {
        return driver.getPageSource().contains("Invoice") || driver.getPageSource().contains("Success");
    }
}
