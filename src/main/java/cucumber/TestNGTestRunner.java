package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\main\\java\\cucumber", glue = "StepDefinition", monochrome = true, tags="@Regression", plugin = {
		"html:target/cucumber.html" })

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
