package com.commonMethods;

import org.openqa.selenium.support.PageFactory;

import com.drivers.DriverUtils;
import com.util.WebDriverActions;

public class UrlCreation extends WebDriverActions {

	public UrlCreation(DriverUtils aDriverUtils, String aDevice) {
		super(aDriverUtils, aDevice);
		PageFactory.initElements(driverUtils.getWebDriver(aDevice), this);
	}

	public String getSiteVersionURL(String label) {

		if (label.equals("sportingbet.co.za")) {
			return "https://virtualsports." + label + "/site/version";
		}

		return "https://virtualsports." + label + ".com/site/version";
	}

	public String getHealthURL(String label) {

		if (label.equals("sportingbet.co.za")) {
			return "https://virtualsports." + label + "/health/report";
		}

		return "https://virtualsports." + label + ".com/health/report";
	}

}
