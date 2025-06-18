package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/4_checkout.feature",
    glue = "stepDefinitions",
    plugin = {"pretty", "html:target/cucumber-checkout-reports.html"},
    tags = "@checkout"
)
public class RunCheckoutTest {
    // Runner untuk test suite checkout
} 