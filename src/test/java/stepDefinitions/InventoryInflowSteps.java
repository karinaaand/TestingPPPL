package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.InventoryPage;
import pages.CheckoutPage;
import pages.HistoryPage;
import pages.DashboardPage;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;

public class InventoryInflowSteps {
   private WebDriver driver;
   private InventoryPage inventoryPage;
   private CheckoutPage checkoutPage;
   private HistoryPage historyPage;
   private DashboardPage dashboardPage;

   private void initializePages() {
       if (driver == null) {
           driver = WebDriverManager.getDriver();
       }
       if (inventoryPage == null) {
           inventoryPage = new InventoryPage(driver);
           checkoutPage = new CheckoutPage(driver);
           historyPage = new HistoryPage(driver);
           dashboardPage = new DashboardPage(driver);
       }
   }

   @Given("Pengguna berada pada halaman {string}")
   public void pengguna_berada_pada_halaman(String halaman) {
       initializePages();
       // Login ke dashboard
       driver.get("http://127.0.0.1:8000/login");
       driver.findElement(By.name("email")).sendKeys("admin@simbat.disyfa.site");
       driver.findElement(By.name("password")).sendKeys("admin");
       driver.findElement(By.xpath("//button[contains(text(), 'Masuk')]")).click();
       // Setelah login, langsung ke halaman Barang Masuk
       driver.get("http://127.0.0.1:8000/inventory/inflows");
   }

   @When("Pengguna klik tombol {string}")
   public void pengguna_klik_tombol(String tombol) {
       initializePages();
       if (tombol.equalsIgnoreCase("Tambah")) {
           inventoryPage.clickTambahButton();
       }
       // Tambahkan logic untuk tombol lain jika perlu
   }

   @When("Pengguna mengisi data vendor, tanggal barang masuk, nama obat, jumlah, tanggal expired, dan memilih metode pembayaran")
   public void pengguna_mengisi_data_barang_masuk() {
       initializePages();
       // Contoh data valid
       inventoryPage.fillInventoryForm("Paracetamol 500mg", "10", "2025-12-31");
   }

   @When("Pengguna mengisi data vendor, tanggal barang masuk, nama obat, jumlah negatif atau 0, tanggal expired, dan memilih metode pembayaran")
   public void pengguna_mengisi_data_barang_masuk_negatif() {
       initializePages();
       // Contoh data tidak valid
       inventoryPage.fillInventoryForm("Paracetamol 500mg", "0", "2025-12-31");
   }

   @When("Pengguna mengisi data vendor, tanggal barang masuk, nama obat, jumlah, tanggal expired sudah lewat, dan memilih metode pembayaran")
   public void pengguna_mengisi_data_barang_masuk_expired() {
       initializePages();
       // Contoh data expired
       inventoryPage.fillInventoryForm("Paracetamol 500mg", "10", "2020-01-01");
   }

   @When("Pengguna klik tombol tambah")
   public void pengguna_klik_tombol_tambah() {
       initializePages();
       inventoryPage.addDrugItem();
   }

   @When("Pengguna klik tombol simpan")
   public void pengguna_klik_tombol_simpan() {
       initializePages();
       inventoryPage.clickSimpanButton();
   }

   @When("Pengguna klik simpan di Inventory dan konfirmasi simpan data")
   public void pengguna_konfirmasi_simpan_data() {
       // Sudah di-handle di clickSimpanButton
   }

   @Then("Data barang masuk berhasil ditambahkan dengan output Laporan Penerimaan Barang")
   public void data_barang_masuk_berhasil_ditambahkan() {
       String pageSource = driver.getPageSource().toLowerCase();
       boolean found = pageSource.contains("laporan penerimaan barang") ||
                       pageSource.contains("penerimaan") ||
                       pageSource.contains("lpb");
       assert found;
   }

   @When("Pengguna klik tombol \"View\" pada kolom \"Action\" di barang masuk")
   public void pengguna_klik_tombol_view_di_barang_masuk() {
       initializePages();
       // Tunggu tabel termuat
       new WebDriverWait(driver, Duration.ofSeconds(10))
           .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table tbody tr")));
       // Selector tombol view (ikon mata) pada baris pertama
       By viewButton = By.xpath("//table//tbody/tr[1]//td[contains(@class,'flex') and contains(@class,'justify-center')]//a[contains(@class,'bg-blue-500')]");
       new WebDriverWait(driver, Duration.ofSeconds(10))
           .until(ExpectedConditions.elementToBeClickable(viewButton));
       driver.findElement(viewButton).click();
       // Tunggu URL berubah ke halaman detail LPB
       new WebDriverWait(driver, Duration.ofSeconds(10))
           .until(ExpectedConditions.urlContains("/inventory/inflows/"));
   }

   @Then("Menampilkan Laporan Penerimaan Barang")
   public void menampilkan_laporan_penerimaan_barang() {
       // Validasi hanya dengan URL
       String currentUrl = driver.getCurrentUrl();
       assert currentUrl.contains("/inventory/inflows/");
       try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
   }

   @When("Pengguna klik angka 2 pada paginasi atau klik tanda panah \">\"")
   public void pengguna_klik_paginasi() {
       initializePages();
       By page2 = By.xpath("//a[contains(@aria-label,'Go to page 2') or text()='2']");
       WebElement page2Btn = driver.findElement(page2);
       page2Btn.click();
       new WebDriverWait(driver, Duration.ofSeconds(10))
           .until(ExpectedConditions.urlContains("page=2"));
       try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
   }

   @Then("Sistem menampilkan halaman kedua dari data barang masuk")
   public void sistem_menampilkan_halaman_kedua() {
       // Cek halaman kedua aktif
       assert driver.getCurrentUrl().contains("page=2");
   }

   @Then("Sistem menolak dengan pesan {string}")
   public void sistem_menolak_dengan_pesan(String pesan) {
       assert driver.getPageSource().contains(pesan);
   }
}
