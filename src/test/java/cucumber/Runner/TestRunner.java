package cucumber.Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "src/test/java/features/"
        , glue = "stepDefinitions"
        , plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/TestReport.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}