package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/login_only.feature",
    glue = "stepDefinitions",
    plugin = {"pretty", "html:target/cucumber-loginonly-reports.html"},
    tags = "@login"
)
public class RunLoginOnlyTest {
    // Kosong, hanya untuk runner login saja
} 