# TestingPPPL - Automated Testing for SIMBAT 🚀

Selamat datang di proyek **TestingPPPL**! 

Proyek ini adalah _automated testing suite_ untuk aplikasi web SIMBAT (Sistem Informasi Manajemen Barang dan Transaksi) menggunakan **Java**, **Selenium WebDriver**, dan **Cucumber (BDD)**. Dengan pendekatan _Behavior Driven Development_, pengujian menjadi lebih mudah dipahami, dikembangkan, dan didokumentasikan.

---

## ✨ Fitur Utama
- Pengujian otomatis untuk skenario login, inventory, checkout, dan riwayat transaksi
- Mendukung skenario positif & negatif
- Menggunakan _Page Object Model_ untuk pemeliharaan kode yang lebih baik
- Laporan hasil pengujian otomatis (HTML)
- Contoh skenario BDD dengan Gherkin

---

## 🛠️ Teknologi & Library
- Java 23
- Maven
- Selenium WebDriver 4.29.0
- Cucumber (Gherkin) 7.22.2
- JUnit 5.11.4
- TestNG 7.11.0
- ExtentReports 5.0.9

---

## 📁 Struktur Proyek
```
TestingPPPL/
├── src/
│   ├── main/java/pages/         # (Page Object, kosong)
│   └── test/java/
│        ├── pages/             # Page Object untuk pengujian
│        ├── stepDefinitions/   # Step Definition Cucumber
│        └── runners/           # (Runner, jika ada)
│   └── test/resources/features/ # File .feature (Gherkin)
├── pom.xml                     # Konfigurasi Maven & dependencies
└── README.md                   # Dokumentasi proyek
```

---

## 🚀 Cara Menjalankan
1. Clone repo ini
2. Install dependencies
   ```bash
   mvn clean install
   ```
3. Jalankan pengujian
   ```bash
   mvn test
   ```
4. Lihat laporan hasil
   - Buka `target/cucumber-reports.html` di browser

---

## 📝 Contoh Skenario Pengujian (Gherkin)
```gherkin
Feature: SIMBAT Login Functionality
  As a user
  I want to be able to login to the SIMBAT system
  So that I can access the system's features

  @login @positive
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid email "admin@simbat.disyfa.site"
    And I enter valid password "admin"
    And I click the login button
    Then I should be logged in successfully

  @login @negative
  Scenario Outline: Failed login with invalid credentials
    Given I am on the login page
    When I enter email "<email>"
    And I enter password "<password>"
    And I click the login button
    Then I should remain on the login page
    And the login form should be empty

    Examples:
      | email                    | password           |
      | apalah123@gmail.com     | admin             |
      | admin@simbat.disyfa.site| linggangguliguli  |
      |                         | admin             |
      | admin@simbat.disyfa.site|                   |
      |                         |                   |
```

---

## 🔎 Pengujian Login Lanjutan (Advanced)
Untuk pengujian login lanjutan dengan skenario negatif dan validasi lebih mendalam, silakan kunjungi:
👉 [Testing_PPPL_Login (GitHub)](https://github.com/dimalahmad/Testing_PPPL_Login)

Repositori tersebut berisi skenario pengujian login yang lebih advance, termasuk validasi kredensial invalid, form kosong, dan penanganan error.

---

## 👨‍💻 Kontribusi
Kontribusi sangat terbuka! Silakan _fork_, buat _branch_, dan ajukan _pull request_.

---

## 📢 Lisensi
Proyek ini menggunakan lisensi MIT.

---

Happy Testing! 🎉 