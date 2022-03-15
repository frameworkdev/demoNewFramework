package com.pages;

import org.openqa.selenium.WebDriver;

import com.commonMethods.StringOperationMethods;
import com.drivers.DriverUtils;
import com.pages.web.*;

public class PageObjectManagerWeb {
	
	private DriverUtils driverUtils;
	private String device;
	private WebHomePage webHomePage;
	private StringOperationMethods stringOperationMethods;
	
	public PageObjectManagerWeb(DriverUtils aDriverUtils, String deviceName) {
		driverUtils = aDriverUtils;
		device = deviceName;
	}
	
	public WebDriver getWebDriver() {
		return driverUtils.getWebDriver(device);
	}
	
	public StringOperationMethods getStringOperationMethods() {
		return (stringOperationMethods == null) ? stringOperationMethods = new StringOperationMethods(driverUtils, device) : stringOperationMethods;
	}
	
	public WebHomePage getWebHomePage() {
		return (webHomePage == null) ? webHomePage = new WebHomePage(driverUtils, device) : webHomePage;
	}

}
