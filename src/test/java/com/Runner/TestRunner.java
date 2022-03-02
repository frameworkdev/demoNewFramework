/**
 * 
 */
package com.Runner;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.StepDefinitions.Hooks;
import com.constants.Constants;
import com.util.EmailReport;

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

	@Test(groups = "cucumber", description = "Executions of Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		System.out.println("Inside testng after class");
		testNGCucumberRunner.finish();
		
		log.info("### END OF EXECUTION : {}, Time taken:{}, ###", TestRunner.class.getSimpleName(), formatDuration(Constants.sysTime - Hooks.startTime, "HH:mm:ss"));
		
		log.info("############## Email Phase started #############");
		EmailReport.sendEmailReports();
	}

}
