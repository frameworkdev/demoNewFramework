package com.StepDefinitions.web;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.constants.Constants;
import com.constants.StringConstants;
import com.drivers.DriverUtils;
import com.pages.PageObjectManagerWeb;
import com.util.Action;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SBCOM extends Action {

	private DriverUtils driverUtils;
	private PageObjectManagerWeb pomWeb;
	public String versionInfo;

	private static final Logger log = LogManager.getLogger(SBCOM.class.getName());

	public SBCOM(DriverUtils aDriverUtils) {
		driverUtils = aDriverUtils;
		pomWeb = new PageObjectManagerWeb(driverUtils, Constants.DEVICE);
	}

	@Given("^enter url$")
	public void enter_url() throws Throwable {
		try{
			pomWeb.getWebDriver().get(Constants.URL);
			versionInfo = pomWeb.getStringOperationMethods().splitStringFromText(pomWeb.getWebHomePage().txt_mobileGambling.getText());
			log.debug(versionInfo);
			
		}catch(Exception e){
			log.debug(e.getMessage());
		}

	}

	@Then("^verify site version$")
	public void verify_site_version() throws Throwable {
		
		try{
			Assert.assertEquals(versionInfo, StringConstants.webVersionCheck);
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("Verified");
			
		}catch(Exception e){
			
		}
	}
}
