package com.pages.web;

import org.apache.log4j.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.drivers.DriverUtils;
import com.util.WebDriverActions;

public class WebHomePage extends WebDriverActions{

	private static final Logger log = LogManager.getLogger(WebHomePage.class.getName());
	
	public WebHomePage(DriverUtils aDriverUtils, String aDevice) {
		super(aDriverUtils, aDevice);
		System.out.println(aDriverUtils.getWebDriver(aDevice));
		PageFactory.initElements(driverUtils.getWebDriver(), this);
		
	}
	
	@FindBy(xpath = "//*[]")
	public WebElement mobileGameing;

}
