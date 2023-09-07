package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//if we want to run our test in cucumber there is one methods is here cucumberoptions


@CucumberOptions(features="src/test/java/Cucumber",glue="SeleniumAutomation.StepDefinition",
monochrome=true ,tags="@Regression", plugin= {"html:target/cucumber.html"})   //this is mandatory to give then only testRunner will work

public class TestNGTestRunner extends AbstractTestNGCucumberTests //we are extends because it used to run our testNG test in cucumber
{

			
	}
