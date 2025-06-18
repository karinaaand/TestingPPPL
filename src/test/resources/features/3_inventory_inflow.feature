@inventory_inflow
Feature: Inventory - Barang Masuk

  As an inventory admin
  I want to manage incoming goods (Barang Masuk)
  So that inventory data is always up to date

  Background:
    Given Pengguna berada pada halaman "Barang Masuk"

  @positive
  Scenario: Tambah Barang Masuk Baru
    And I add new stock with the following details
      | vendor      | medicineName      | paymentMethod | quantity | unit | price  | expiryDate |
      | Vendor Test | Paracetamol 500mg | Cash          | 100      | Pcs  | 500000 | 2025-12-31 |
    And I save the inventory
    Then Data barang masuk berhasil ditambahkan dengan output Laporan Penerimaan Barang

  @positive
  Scenario: Melihat Laporan Penerimaan Barang
    When Pengguna klik tombol "View" pada kolom "Action" di barang masuk
    Then Menampilkan Laporan Penerimaan Barang

  @negative
  Scenario: Tambah Barang Masuk dengan jumlah negatif atau 0
    When Pengguna klik tombol "Tambah"
    And Pengguna mengisi data vendor, tanggal barang masuk, nama obat, jumlah negatif atau 0, tanggal expired, dan memilih metode pembayaran
    And Pengguna klik tombol tambah
    Then Sistem menolak dengan pesan "Jumlah harus lebih dari 0"

  @negative
  Scenario: Tambah Barang Masuk dengan tanggal expired sudah lewat
    When Pengguna klik tombol "Tambah"
    And Pengguna mengisi data vendor, tanggal barang masuk, nama obat, jumlah, tanggal expired sudah lewat, dan memilih metode pembayaran
    And Pengguna klik tombol tambah
    Then Sistem menolak dengan pesan "Tanggal expired tidak boleh di masa lalu" 