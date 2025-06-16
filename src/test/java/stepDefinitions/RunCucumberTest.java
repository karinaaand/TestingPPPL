package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Ini adalah kelas Runner.
 * Kelas ini tidak berisi logika tes, tugasnya hanya untuk
 * menjalankan file .feature dengan konfigurasi yang ditentukan.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        // Menentukan lokasi file .feature (skenario Gherkin)
        features = "src/test/resources/features",

        // Menentukan package tempat Step Definitions (kode implementasi)
        glue = "stepDefinitions",

        // Plugin untuk membuat laporan tes yang rapi
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class RunCucumberTest {
    // Biarkan kelas ini kosong. Tidak perlu ada isi di dalamnya.
}