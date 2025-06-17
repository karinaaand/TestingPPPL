# 🚀 TestingPPPL

Proyek ini berisi pengujian otomatis untuk aplikasi web SIMBAT (Sistem Informasi Manajemen Barang dan Transaksi) menggunakan Selenium WebDriver dan Cucumber.

---

## 📝 Deskripsi

Proyek ini menguji alur kerja lengkap aplikasi SIMBAT, mulai dari login, manajemen inventaris, checkout, hingga riwayat transaksi. Pengujian dilakukan dengan pendekatan BDD (Behavior-Driven Development) menggunakan Cucumber.

---

## ✨ Fitur yang Diuji

- 🔐 **Login**: Validasi login dengan kredensial valid dan invalid.
- 🏠 **Dashboard**: Navigasi dan verifikasi halaman dashboard setelah login.
- 📦 **Inventory**: Penambahan stok baru, pengisian form inventaris, dan penyimpanan data.
- 🏥 **Klinik**: Penambahan stok untuk klinik, pemilihan vendor, metode pembayaran, dan pengaturan tanggal.
- 🛒 **Checkout**: Proses checkout dengan pemilihan tipe (Inventory/Clinic), penambahan item, dan verifikasi invoice.
- 📜 **History**: Navigasi ke halaman riwayat transaksi dan verifikasi data transaksi.

---

## 🛠️ Teknologi yang Digunakan

- ☕ Java 23
- 🕸️ Selenium WebDriver 4.29.0
- 🥒 Cucumber 7.22.2
- 🧪 JUnit 5.11.4
- 🧪 TestNG 7.11.0
- 📊 ExtentReports 5.0.9

---

## 📁 Struktur Proyek

- `src/test/java/pages/` — Page Object Model (POM) untuk setiap halaman aplikasi
- `src/test/java/stepDefinitions/` — Implementasi step definition untuk skenario Cucumber
- `src/test/resources/features/` — File feature Cucumber (Gherkin)

---

## ▶️ Cara Menjalankan Pengujian

1. 💻 Pastikan Java 23 dan Maven terinstal di sistem Anda.
2. ⬇️ Clone repositori ini.
3. 🖥️ Buka terminal dan navigasi ke direktori proyek.
4. ▶️ Jalankan perintah berikut untuk menjalankan pengujian:

   ```bash
   mvn test
   ```

5. 📑 Laporan pengujian akan tersedia di `target/cucumber-reports.html`.

---

## ⚠️ Catatan

- 🖥️ Pastikan browser Chrome terinstal dan versi ChromeDriver sesuai dengan versi Chrome Anda.
- 🌐 Pengujian menggunakan URL `https://simbat.madanateknologi.web.id/login` sebagai target aplikasi.

---

## 🔎 Pengujian Login Lanjutan (Advanced)

Untuk pengujian login lanjutan dengan skenario negatif, silakan kunjungi [Testing_PPPL_Login](https://github.com/dimalahmad/Testing_PPPL_Login). Repositori tersebut berisi skenario pengujian login yang lebih mendalam, termasuk validasi kredensial invalid dan penanganan kesalahan.

---

🎉 **Happy Testing!** 🎉 