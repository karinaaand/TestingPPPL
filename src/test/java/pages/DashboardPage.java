package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private final WebDriverWait wait;

    // --- Locators ---
    // Gunakan locator yang sama dengan tes Anda yang berhasil
    private final By profileButton = By.cssSelector("button[onclick='toggleModal()']");
    private final By logoutLink = By.linkText("Keluar");

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Aksi pada Halaman ---

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
    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
