package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber->  TestNG

@CucumberOptions(features = "src/test/java/cucumber", glue = "org.LetsShop.stepDefinitions", monochrome = true, tags = "@ErrorValidation", plugin = {
		"html:CucumberReport/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
// tags = "@Regression"
}
