package com.StepDefinitions.web;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.commonMethods.UrlCreation;
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
	public String sitecoreService;
	public String contentTemplates;

	private static final Logger log = LogManager.getLogger(SBCOM.class.getName());

	public SBCOM(DriverUtils aDriverUtils) {
		driverUtils = aDriverUtils;
		pomWeb = new PageObjectManagerWeb(driverUtils, Constants.DEVICE);
	}

	@Given("^enter \"([^\"]*)\" site version url$")
	public void enter_url(String arg1) {
		try {
			String siteVersionURL = pomWeb.getUrlCreation().getSiteVersionURL(arg1);
			pomWeb.getWebDriver().get(siteVersionURL);
			versionInfo = pomWeb.getStringOperationMethods()
					.splitStringFromText(pomWeb.getWebHomePage().txt_mobileGambling.getText());
			log.debug(versionInfo);
		} catch (Exception e) {
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("Failed");
			log.debug(e.getMessage());
			Assert.fail();
		}
	}

	@Then("^verify site version$")
	public void verify_site_version() {

		try {
			Assert.assertEquals(versionInfo, StringConstants.webVersionCheck);
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("Verified");
		} catch (AssertionError e) {
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("Failed");
			Assert.fail();
		}
	}

	@Given("^enter \"([^\"]*)\" health url$")
	public void enter_health_url(String arg1) {
		try {
			String healthURL = pomWeb.getUrlCreation().getHealthURL(arg1);
			pomWeb.getWebDriver().get(healthURL);
			pomWeb.getWebHomePage().waitForElement(pomWeb.getWebHomePage().clr_SitecoreService,180);
			sitecoreService = pomWeb.getWebHomePage().clr_SitecoreService.getCssValue("color");
			contentTemplates = pomWeb.getWebHomePage().clr_ContentTemplates.getCssValue("color");
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("verified");
		} catch (Exception e) {
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("Unable to verify health page");
			log.debug(e.getMessage());
			Assert.fail();
		}

	}

	@Then("^verify color of Sitecore service$")
	public void verify_color_of_Sitecore_service() {
		try {
			Assert.assertEquals(sitecoreService, StringConstants.healthColorCheck);
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("Health check verified");
		} catch (AssertionError e) {
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("Health check failed");
			log.debug(e.getMessage());
			Assert.fail();
		}

	}

	@Then("^verify color of Content Templates$")
	public void verify_color_of_Content_Templates() {
		try {
			Assert.assertEquals(contentTemplates, StringConstants.healthColorCheck);
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("Health check verified");
		} catch (AssertionError e) {
			pomWeb.getWebHomePage().addCommentAndScreenshot_Web("Health check failed");
			log.debug(e.getMessage());
			Assert.fail();
		}
	}

}
