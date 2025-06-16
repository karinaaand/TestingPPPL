package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HistoryPage {
    WebDriver driver;
    private WebDriverWait wait;

    private By historyButton = By.xpath("//a[contains(@href, '/log')]");

    public HistoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToHistory() {
        try {
            wait.until(ExpectedConditions.urlContains("/transaction/create"));
            Thread.sleep(2000);
            WebElement historyBtn = wait.until(ExpectedConditions.elementToBeClickable(historyButton));
            historyBtn.click();
            Thread.sleep(2000);

        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to history page", e);
        }
    }
}
