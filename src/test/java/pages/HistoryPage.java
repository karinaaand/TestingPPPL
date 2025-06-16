package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HistoryPage {
    WebDriver driver;

    public HistoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHistory() {
        driver.findElement(By.linkText("History")).click();
    }

    public boolean isTransactionShown(String itemName) {
        return driver.getPageSource().contains(itemName);
    }
}
