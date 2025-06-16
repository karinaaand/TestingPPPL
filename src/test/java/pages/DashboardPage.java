package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By profileButton = By.xpath("//button[contains(@class, 'flex') and contains(@class, 'items-center') and contains(@class, 'w-10') and contains(@class, 'h-10')]");
    private final By logoutLink = By.xpath("//a[contains(@href, '/logout')]");
    private By headerTitle = By.xpath("//h1[contains(@class, 'text-2xl')]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Metode ini memverifikasi bahwa kita sudah berada di halaman dashboard
     * dengan cara menunggu hingga tombol profil muncul dan bisa diklik.
     * Ini adalah bukti login yang kuat.
     */
    public void verifyOnDashboardPage() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
    }

    /**
     * Metode untuk melakukan proses logout.
     */
    public void clickLogoutButton() {
        try {
            WebElement profileBtn = wait.until(ExpectedConditions.elementToBeClickable(profileButton));
            profileBtn.click();
            Thread.sleep(1000);
            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
            logoutBtn.click();
            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Failed to logout", e);
        }
    }

    public boolean isDashboardDisplayed() {
        try {
            WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(headerTitle));
            String currentUrl = driver.getCurrentUrl();
            return titleElement.isDisplayed() && currentUrl.contains("/dashboard");
        } catch (Exception e) {
            return false;
        }
    }

}
