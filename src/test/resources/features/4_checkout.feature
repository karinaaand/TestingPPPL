@checkout
Feature: Checkout - Transaksi Barang

  As a cashier/admin
  I want to process checkout transactions
  So that sales transactions can be completed properly

  Background:
    Given Pengguna berada pada halaman Checkout Barang

  @positive
  Scenario: Checkout Obat dengan Data Valid dari Inventory
    When Pengguna memilih checkout barang dari "Inventory"
    And Pengguna mengisi diskon transaksi "10"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pack"
    And Pengguna mengisi kolom jumlah "5"
    And Pengguna klik tombol checkout "Tambah"
    And Pengguna klik tombol checkout "Checkout"
    And Pengguna konfirmasi checkout
    Then Invoice akan masuk ke dalam History bagian checkout barang

  @positive
  Scenario: Checkout Obat tanpa Diskon dan 1 pcs dari Inventory
    When Pengguna memilih checkout barang dari "Inventory"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "1"
    And Pengguna klik tombol checkout "Tambah"
    And Pengguna klik tombol checkout "Checkout"
    And Pengguna konfirmasi checkout
    Then Invoice akan masuk ke dalam History bagian checkout barang

  @positive
  Scenario: Checkout Obat dengan Diskon Rupiah Valid dari Inventory
    When Pengguna memilih checkout barang dari "Inventory"
    And Pengguna memilih diskon rupiah
    And Pengguna mengisi diskon transaksi "1000"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "1"
    And Pengguna klik tombol checkout "Tambah"
    And Pengguna klik tombol checkout "Checkout"
    And Pengguna konfirmasi checkout
    Then Invoice akan masuk ke dalam History bagian checkout barang

  @negative
  Scenario: Checkout Obat dengan Jumlah Melebihi Stok dari Inventory
    When Pengguna memilih checkout barang dari "Inventory"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "999999"
    And Pengguna klik tombol checkout "Tambah"
    Then Sistem menampilkan pesan "Stok tidak mencukupi"

  @negative
  Scenario: Checkout dengan Item Kosong dari Inventory
    When Pengguna memilih checkout barang dari "Inventory"
    And Pengguna kosongkan item
    And Pengguna klik tombol checkout "Tambah"
    Then Sistem menampilkan pesan "Silakan pilih obat terlebih dahulu"

  @negative
  Scenario: Checkout dengan Diskon Tidak Valid (Lebih dari 100%) dari Inventory
    When Pengguna memilih checkout barang dari "Inventory"
    And Pengguna mengisi diskon transaksi "150"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "2"
    And Pengguna klik tombol checkout "Tambah"
    And Pengguna klik tombol checkout "Checkout"
    And Pengguna konfirmasi checkout
    Then Sistem menampilkan pesan "Diskon tidak valid"

  @negative
  Scenario: Checkout dengan Diskon Negatif dari Inventory
    When Pengguna memilih checkout barang dari "Inventory"
    And Pengguna mengisi diskon transaksi "-10"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "1"
    And Pengguna klik tombol checkout "Tambah"
    Then Sistem menampilkan pesan "Diskon tidak boleh negatif"

  @negative
  Scenario: Checkout Obat dengan Diskon Rupiah Negatif di Inventory
    When Pengguna memilih checkout barang dari "Inventory"
    And Pengguna memilih diskon rupiah
    And Pengguna mengisi diskon transaksi "-1000"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "1"
    And Pengguna klik tombol checkout "Tambah"
    Then Sistem menampilkan pesan "Diskon tidak boleh negatif"

  @positive
  Scenario: Checkout Obat dengan Data Valid dari Klinik
    When Pengguna memilih checkout barang dari "Klinik"
    And Pengguna mengisi diskon transaksi "10"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pack"
    And Pengguna mengisi kolom jumlah "5"
    And Pengguna klik tombol checkout "Tambah"
    And Pengguna klik tombol checkout "Checkout"
    And Pengguna konfirmasi checkout
    Then Invoice akan masuk ke dalam History bagian checkout barang

  @positive
  Scenario: Checkout Obat tanpa Diskon dan 1 pcs dari Klinik
    When Pengguna memilih checkout barang dari "Klinik"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "1"
    And Pengguna klik tombol checkout "Tambah"
    And Pengguna klik tombol checkout "Checkout"
    And Pengguna konfirmasi checkout
    Then Invoice akan masuk ke dalam History bagian checkout barang

  @positive
  Scenario: Checkout Obat dengan Diskon Rupiah Valid di Klinik
    When Pengguna memilih checkout barang dari "Klinik"
    And Pengguna memilih diskon rupiah
    And Pengguna mengisi diskon transaksi "1000"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "1"
    And Pengguna klik tombol checkout "Tambah"
    And Pengguna klik tombol checkout "Checkout"
    And Pengguna konfirmasi checkout
    Then Invoice akan masuk ke dalam History bagian checkout barang

  @negative
  Scenario: Checkout Obat dengan Jumlah Melebihi Stok dari Klinik
    When Pengguna memilih checkout barang dari "Klinik"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "999999"
    And Pengguna klik tombol checkout "Tambah"
    Then Sistem menampilkan pesan "Stok tidak mencukupi"

  @negative
  Scenario: Checkout dengan Item Kosong dari Klinik
    When Pengguna memilih checkout barang dari "Klinik"
    And Pengguna kosongkan item
    And Pengguna klik tombol checkout "Tambah"
    Then Sistem menampilkan pesan "Silakan pilih obat terlebih dahulu"

  @negative
  Scenario: Checkout dengan Diskon Tidak Valid (Lebih dari 100%) dari Klinik
    When Pengguna memilih checkout barang dari "Klinik"
    And Pengguna mengisi diskon transaksi "150"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "2"
    And Pengguna klik tombol checkout "Tambah"
    And Pengguna klik tombol checkout "Checkout"
    And Pengguna konfirmasi checkout
    Then Sistem menampilkan pesan "Diskon tidak valid"

  @negative
  Scenario: Checkout dengan Diskon Negatif dari Klinik
    When Pengguna memilih checkout barang dari "Klinik"
    And Pengguna mengisi diskon transaksi "-10"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "1"
    And Pengguna klik tombol checkout "Tambah"
    Then Sistem menampilkan pesan "Diskon tidak boleh negatif"

  @negative
  Scenario: Checkout Obat dengan Diskon Rupiah Negatif di Klinik
    When Pengguna memilih checkout barang dari "Klinik"
    And Pengguna memilih diskon rupiah
    And Pengguna mengisi diskon transaksi "-1000"
    And Pengguna mencari nama item "Paracetamol 500mg 1 pcs"
    And Pengguna mengisi kolom jumlah "1"
    And Pengguna klik tombol checkout "Tambah"
    Then Sistem menampilkan pesan "Diskon tidak boleh negatif"

  @positive
  Scenario: History Invoice Checkout dari Inventory (Melihat dan Cetak Invoice)
    When Pengguna klik tombol checkout "History"
    And Pengguna klik tombol "View" pada kolom "Action"
    Then Menampilkan halaman invoice
    And Pengguna klik tombol checkout "Cetak"
    And Pengguna memilih format cetak "PDF"
    And Pengguna konfirmasi cetak
    Then Invoice barang yang berhasil di cetak 