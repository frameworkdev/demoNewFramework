package com.StepDefinitions.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.StepDefinitions.mobile.SampleMobile;
import com.constants.Constants;
import com.drivers.DriverUtils;
import com.pages.PageObjectManagerWeb;
import com.util.Action;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SBCOM extends Action {

	private int count = 0;
	private DriverUtils driverUtils;
	private PageObjectManagerWeb pomWeb;

	private static final Logger log = LogManager.getLogger(SampleMobile.class.getName());

	public SBCOM(DriverUtils aDriverUtils) {
		driverUtils = aDriverUtils;
		pomWeb = new PageObjectManagerWeb(driverUtils, Constants.DEVICE);
	}

	@Given("^enter url$")
	public void enter_url() throws Throwable {
		try{
			pomWeb.getWebDriver().get("https://virtualsports.sportingbet.com/site/version");
			String versionInfo = pomWeb.getWebHomePage().mobileGameing.getText();
			String[] s = versionInfo.split(" ");
			System.out.println(s[1]);
			
		}catch(Exception e){
			
		}

	}

	@Then("^verify site version$")
	public void verify_site_version() throws Throwable {
		
		try{
			//Assert.assertEquals(actual, expected);
			
		}catch(Exception e){
			
		}
	}
}
