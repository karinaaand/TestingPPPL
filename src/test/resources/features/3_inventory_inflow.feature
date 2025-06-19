@inflow
Feature: Inventory Inflow Management
  As a warehouse administrator
  I want to manage incoming inventory
  So that I can track and record new stock arrivals

  Background:
    Given Pengguna berada pada halaman "Barang Masuk"

  @inflow-positive
  Scenario: Add new inventory stock successfully
    When Pengguna klik tombol "Tambah"
    And Pengguna mengisi data vendor, tanggal barang masuk, nama obat, jumlah, tanggal expired, dan memilih metode pembayaran
    And Pengguna klik tombol tambah
    And Pengguna klik tombol simpan
    And Pengguna klik simpan di Inventory dan konfirmasi simpan data
    Then Data barang masuk berhasil ditambahkan dengan output Laporan Penerimaan Barang

  @inflow-view
  Scenario: View inventory inflow details
    When Pengguna klik tombol "View" pada kolom "Action" di barang masuk
    Then Menampilkan Laporan Penerimaan Barang

  @inflow-pagination
  Scenario: Navigate through inventory pages
    When Pengguna klik angka 2 pada paginasi atau klik tanda panah ">"
    Then Sistem menampilkan halaman kedua dari data barang masuk

  @inflow-negative
  Scenario: Add inventory with invalid quantity
    When Pengguna klik tombol "Tambah"
    And Pengguna mengisi data vendor, tanggal barang masuk, nama obat, jumlah negatif atau 0, tanggal expired, dan memilih metode pembayaran
    And Pengguna klik tombol tambah
    Then Sistem menolak dengan pesan "Jumlah harus lebih dari 0"

  @inflow-expired
  Scenario: Add inventory with expired date
    When Pengguna klik tombol "Tambah"
    And Pengguna mengisi data vendor, tanggal barang masuk, nama obat, jumlah, tanggal expired sudah lewat, dan memilih metode pembayaran
    And Pengguna klik tombol tambah
    Then Sistem menolak dengan pesan "Tanggal expired tidak boleh di masa lalu"
