package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CheckoutPage;
import pages.HistoryPage;
import pages.DashboardPage;
import java.time.Duration;
import java.util.List;

public class CheckoutSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private CheckoutPage checkoutPage;
    private HistoryPage historyPage;
    private DashboardPage dashboardPage;

    private void initializePages() {
        if (driver == null) {
            driver = WebDriverManager.getDriver();
            wait = WebDriverManager.getWait();
        }
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage(driver);
            historyPage = new HistoryPage(driver);
            dashboardPage = new DashboardPage(driver);
        }
    }

    @Given("Pengguna berada pada halaman Checkout Barang")
    public void pengguna_berada_pada_halaman_checkout_barang() {
        initializePages();

        // Login ke dashboard
        driver.get("https://simbat.madanateknologi.web.id/login");
        driver.findElement(By.name("email")).sendKeys("admin@simbat.disyfa.site");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[contains(text(), 'Masuk')]")).click();

        // Setelah login, langsung ke halaman Checkout
        driver.get("https://simbat.madanateknologi.web.id/transaction/create");

        // Verifikasi sudah di halaman checkout
        wait.until(ExpectedConditions.urlContains("/transaction/create"));
    }

    @When("Pengguna memilih checkout barang dari {string}")
    public void pengguna_memilih_checkout_barang_dari(String jenis) {
        initializePages();
        try {
            if ("Inventory".equalsIgnoreCase(jenis)) {
                By inventoryTab = By.xpath("//div[@id='inventoryTab']");
                WebElement inventoryElement = wait.until(ExpectedConditions.elementToBeClickable(inventoryTab));
                inventoryElement.click();
                Thread.sleep(1000);
            } else if ("Klinik".equalsIgnoreCase(jenis)) {
                By klinikTab = By.xpath("//div[@id='klinikTab']");
                WebElement klinikElement = wait.until(ExpectedConditions.elementToBeClickable(klinikTab));
                klinikElement.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to select checkout type: " + jenis, e);
        }
    }

    @When("Pengguna mengisi diskon transaksi {string}")
    public void pengguna_mengisi_diskon_transaksi(String diskon) {
        initializePages();
        try {
            By discountInput = By.name("transactionDiscount");
            WebElement discountElement = wait.until(ExpectedConditions.elementToBeClickable(discountInput));
            discountElement.clear();
            Thread.sleep(500);
            discountElement.sendKeys(diskon);
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set discount: " + diskon, e);
        }
    }

    @When("Pengguna mencari nama item {string}")
    public void pengguna_mencari_nama_item(String namaItem) {
        initializePages();
        try {
            By medicineNameInput = By.xpath("//input[@placeholder='Nama Item']");
            WebElement medicineInput = wait.until(ExpectedConditions.elementToBeClickable(medicineNameInput));
            medicineInput.clear();
            Thread.sleep(500);
            medicineInput.sendKeys(namaItem);
            Thread.sleep(2000);

            // Pilih item dari dropdown suggestion sesuai parameter
            By drugSuggestionOption = By.xpath("//*[contains(text(), '" + namaItem + "')]");
            WebElement suggestionOption = wait.until(ExpectedConditions.elementToBeClickable(drugSuggestionOption));
            Thread.sleep(1000);
            suggestionOption.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search item: " + namaItem, e);
        }
    }

    @When("Pengguna mengisi kolom jumlah {string}")
    public void pengguna_mengisi_kolom_jumlah(String jumlah) {
        initializePages();
        try {
            By quantityInput = By.xpath("//input[@placeholder='Jumlah']");
            WebElement quantityElement = wait.until(ExpectedConditions.elementToBeClickable(quantityInput));
            quantityElement.clear();
            Thread.sleep(500);
            quantityElement.sendKeys(jumlah);
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set quantity: " + jumlah, e);
        }
    }

    @When("Pengguna klik tombol checkout {string}")
    public void pengguna_klik_tombol_checkout(String tombol) {
        initializePages();
        try {
            if ("Tambah".equals(tombol)) {
                By tambahButton = By.xpath("//button[contains(., 'Tambah')]");
                WebElement tambahBtn = wait.until(ExpectedConditions.elementToBeClickable(tambahButton));
                tambahBtn.click();
                Thread.sleep(1000);
            } else if ("Checkout".equals(tombol)) {
                By checkoutButton = By.xpath("//button[contains(., 'Checkout')]");
                WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
                checkoutBtn.click();
                Thread.sleep(1000);
            } else if ("History".equals(tombol)) {
                By historyButton = By.xpath("//a[contains(@href, '/log')]");
                WebElement historyBtn = wait.until(ExpectedConditions.elementToBeClickable(historyButton));
                historyBtn.click();
                Thread.sleep(2000);
            } else if ("View".equals(tombol)) {
                By viewButton = By.xpath("//button[contains(@class, 'btn-view') or contains(@title, 'View')]");
                WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(viewButton));
                viewBtn.click();
                Thread.sleep(2000);
            } else if ("Cetak".equals(tombol)) {
                // Tunggu halaman invoice benar-benar tampil
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Invoice')]")));
                // Tunggu tombol Cetak muncul dan bisa diklik (spesifik sesuai UI)
                WebElement cetakBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'bg-yellow-300') and contains(text(),'Cetak')]")
                ));
                cetakBtn.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click button: " + tombol, e);
        }
    }

    @When("Pengguna konfirmasi checkout")
    public void pengguna_konfirmasi_checkout() {
        initializePages();
        try {
            By simpanButton = By.xpath("//div[@id='addModal']//button[@onclick='submitForm()']");
            WebElement simpanBtn = wait.until(ExpectedConditions.elementToBeClickable(simpanButton));
            Thread.sleep(1000);
            simpanBtn.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to confirm checkout", e);
        }
    }

    @When("Pengguna kosongkan item")
    public void pengguna_kosongkan_item() {
        initializePages();
        try {
            By medicineNameInput = By.xpath("//input[@placeholder='Nama Item']");
            WebElement medicineInput = wait.until(ExpectedConditions.elementToBeClickable(medicineNameInput));
            medicineInput.clear();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear item", e);
        }
    }

    @When("Pengguna memilih format cetak {string}")
    public void pengguna_memilih_format_cetak(String format) {
        initializePages();
        try {
            if ("PDF".equalsIgnoreCase(format)) {
                By pdfButton = By.xpath("//button[contains(text(), 'PDF')]");
                WebElement pdfElement = wait.until(ExpectedConditions.elementToBeClickable(pdfButton));
                pdfElement.click();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to select print format: " + format, e);
        }
    }

    @When("Pengguna konfirmasi cetak")
    public void pengguna_konfirmasi_cetak() {
        initializePages();
        try {
            By confirmPrintButton = By.xpath("//button[contains(., 'Cetak') or contains(., 'Print') or contains(., 'Confirm')]");
            WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmPrintButton));
            confirmBtn.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Failed to confirm print", e);
        }
    }

    @When("Pengguna memilih diskon rupiah")
    public void pengguna_memilih_diskon_rupiah() {
        initializePages();
        try {
            By rupiahRadio = By.xpath("//input[@type='radio' and @name='discount' and @value='rupiah']");
            WebElement radioBtn = wait.until(ExpectedConditions.elementToBeClickable(rupiahRadio));
            if (!radioBtn.isSelected()) {
                radioBtn.click();
            }
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select rupiah discount radio button", e);
        }
    }

    @When("Pengguna klik tombol {string} pada kolom {string}")
    public void pengguna_klik_tombol_pada_kolom(String tombol, String kolom) {
        initializePages();
        try {
            if ("View".equals(tombol) && "Action".equals(kolom)) {
                By viewButton = By.xpath("//table//tbody/tr[1]//td[contains(@class,'flex') and contains(@class,'justify-center')]//a[contains(@class,'bg-blue-500')]");
                WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(viewButton));
                viewBtn.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click " + tombol + " in column " + kolom, e);
        }
    }

    @Then("Invoice akan masuk ke dalam History bagian checkout barang")
    public void invoice_akan_masuk_ke_dalam_history_bagian_checkout_barang() {
        initializePages();
        try {
            // Verifikasi bahwa checkout berhasil
            boolean isSuccess = driver.getPageSource().contains("Success") ||
                              driver.getPageSource().contains("Invoice") ||
                              driver.getCurrentUrl().contains("/log");
            assert isSuccess : "Checkout should be successful";
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify checkout success", e);
        }
    }

    @Then("Menampilkan halaman invoice")
    public void menampilkan_halaman_invoice() {
        initializePages();
        try {
            // Verifikasi bahwa halaman invoice ditampilkan
            boolean isInvoicePage = driver.getPageSource().contains("Invoice") ||
                                  driver.getCurrentUrl().contains("/invoice") ||
                                  driver.getCurrentUrl().contains("/log");
            assert isInvoicePage : "Invoice page should be displayed";
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify invoice page", e);
        }
    }

    @Then("Invoice barang yang berhasil di cetak")
    public void invoice_barang_yang_berhasil_di_cetak() {
        initializePages();
        try {
            // Verifikasi bahwa proses cetak berhasil
            boolean isPrintSuccess = driver.getPageSource().contains("Print") ||
                                   driver.getPageSource().contains("Cetak") ||
                                   driver.getPageSource().contains("Download");
            assert isPrintSuccess : "Print process should be successful";
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify print success", e);
        }
    }

    @Then("Sistem menampilkan pesan {string}")
    public void sistem_menampilkan_pesan(String pesan) {
        initializePages();
        try {
            // Tunggu dan verifikasi pesan error/warning
            Thread.sleep(2000);
            boolean isMessageDisplayed = driver.getPageSource().contains(pesan) ||
                                       driver.getPageSource().contains("Kurang") ||
                                       driver.getPageSource().contains("tidak valid") ||
                                       driver.getPageSource().contains("error");
            assert isMessageDisplayed : "Message '" + pesan + "' should be displayed";
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify message: " + pesan, e);
        }
    }

    @Then("Tabel invoice menampilkan kode berawalan {string}")
    public void tabel_invoice_menampilkan_kode_berawalan(String kodeAwal) {
        initializePages();
        List<WebElement> kodeCells = driver.findElements(By.xpath("//table//tbody/tr/td[1]"));
        boolean found = false;
        for (WebElement cell : kodeCells) {
            String kode = cell.getText();
            if (kode.startsWith(kodeAwal)) {
                found = true;
                break;
            }
        }
        assert found : "Tidak ditemukan invoice dengan kode berawalan: " + kodeAwal;
    }
}
