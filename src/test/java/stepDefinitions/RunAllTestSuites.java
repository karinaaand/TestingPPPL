package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {
        "src/test/resources/features/1_inventory_flow.feature",
        "src/test/resources/features/2_login_only.feature",
        "src/test/resources/features/3_inventory_inflow.feature",
        "src/test/resources/features/4_checkout.feature"
    },
    glue = "stepDefinitions",
    plugin = {"pretty", "html:target/cucumber-all-reports.html"},
    tags = "not @skip"
)
public class RunAllTestSuites {
    // Runner untuk semua test suite
} 