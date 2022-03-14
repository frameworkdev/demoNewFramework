/**
 * 
 */
package com.Runner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/Features",
				 glue = { "com.StepDefinitions", "Hooks" },
				 plugin = {"pretty", "com.cucumber.listener.ExtentCucumberFormatter:reports/Report.html"},
				 monochrome = true, 
				 snippets = SnippetType.CAMELCASE)

@Listeners({com.Runner.CustomListener.class})

public class TestRunner extends  AbstractTestNGCucumberTests {
	
	private static final Logger log = LogManager.getLogger(TestRunner.class.getName());


	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		System.out.println("Inside testng before class");
	}
	
	@DataProvider
	public Object[][] features() {
		System.out.println("In data provider");
		return testNGCucumberRunner.provideFeatures();
	}
	
	@Test(groups = "cucumber", description = "Executions of Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		log.info("In @test -- Testrunner");
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		System.out.println("Inside testng after class");
		testNGCucumberRunner.finish();
	}

}
