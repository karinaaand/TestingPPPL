package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/inventory_inflow.feature",
    glue = "stepDefinitions",
    plugin = {"pretty", "html:target/cucumber-inventoryinflow-reports.html"},
    tags = "@inventory_inflow"
)
public class RunInventoryInflowTest {
    // Kosong, hanya untuk runner barang masuk inventory
} 