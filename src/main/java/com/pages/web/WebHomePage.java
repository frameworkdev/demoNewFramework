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
		PageFactory.initElements(driverUtils.getWebDriver(aDevice), this);
	}
	
	@FindBy(xpath = "//*[contains(text(),'MobileGambling')]")
	public WebElement txt_mobileGambling;
	
	@FindBy(xpath = "//*[text()='Content - Hekaton Cache']")
	public WebElement clr_SitecoreService;
	
	@FindBy(xpath = "//*[text()='Content - Templates']")
	public WebElement clr_ContentTemplates;

}
