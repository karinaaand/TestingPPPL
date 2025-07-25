package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/3_inventory_inflow.feature",
    glue = "stepDefinitions",
    plugin = {"pretty", "html:target/cucumber-inventoryinflow-reports.html"},
    tags = "@inflow"
)
public class RunInventoryInflowTest {
    // Kosong, hanya untuk runner barang masuk inventory
} 