package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object Class untuk Halaman Login.
 * Berisi semua elemen dan layanan yang disediakan oleh halaman ini.
 */
public class LoginPage {

    // Menyimpan instance WebDriver yang akan digunakan
    private final WebDriver driver;

    // --- Locators ---
    // Mendefinisikan locator untuk setiap elemen di halaman sebagai private final By.
    // Ini membuat kode lebih mudah dibaca dan dirawat.
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector("div[role='alert']"); // Contoh jika ada pesan error

    /**
     * Constructor untuk LoginPage.
     * @param driver instance WebDriver yang diteruskan dari step definition.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // --- Aksi pada Halaman (Page Actions / Services) ---

    /**
     * Membuka halaman login.
     */
    public void open() {
        driver.get("https://simbat.madanateknologi.web.id/login");
    }

    /**
     * Memasukkan alamat email ke dalam kolom email.
     * @param email Alamat email pengguna.
     */
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    /**
     * Memasukkan password ke dalam kolom password.
     * @param password Password pengguna.
     */
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Menekan tombol login.
     */
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    /**
     * Metode gabungan untuk melakukan proses login lengkap (Business Flow).
     * Ini menyederhanakan kode di Step Definitions.
     * @param email Alamat email pengguna.
     * @param password Password pengguna.
     */
    public void performLogin(String email, String password) {
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickLoginButton();
    }

    /**
     * Mengambil teks pesan error jika ada.
     * @return String teks pesan error.
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}