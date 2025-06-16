package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object Class untuk Halaman Login.
 * Berisi semua elemen dan layanan yang disediakan oleh halaman ini.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.name("email");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.xpath("//button[contains(text(), 'Masuk')]");
    private final By loginTitle = By.xpath("//h2[contains(text(), 'Masuk')]");

    /**
     * Constructor untuk LoginPage.
     * @param driver instance WebDriver yang diteruskan dari step definition.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Membuka halaman login.
     */
    public void navigateToLoginPage() {
        driver.get("https://simbat.madanateknologi.web.id/login");
    }

    /**
     * Memasukkan alamat email ke dalam kolom email.
     * @param email Alamat email pengguna.
     */
    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.presenceOfElementLocated(emailInput));
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    /**
     * Memasukkan password ke dalam kolom password.
     * @param password Password pengguna.
     */
    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    /**
     * Menekan tombol login.
     */
    public void clickLoginButton() {
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginElement.click();
    }

    /**
     * Mengambil teks pesan error jika ada.
     * @return String teks pesan error.
     */
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert') or contains(@class, 'error') or contains(@class, 'invalid-feedback')]")),
                    ExpectedConditions.urlContains("/dashboard")
            ));

            if (driver.getCurrentUrl().contains("/login")) {
                WebElement errorMessageElement = driver.findElement(By.xpath("//div[contains(@class, 'alert') or contains(@class, 'error') or contains(@class, 'invalid-feedback')]"));
                return errorMessageElement.getText();
            }
            return "Login successful";
        } catch (Exception e) {
            return "No error message found";
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert') or contains(@class, 'error') or contains(@class, 'invalid-feedback')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPageDisplayed() {
        try {
            WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(loginTitle));
            return title.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}