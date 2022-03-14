package com.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import com.pages.PageObjectManagerWeb;

public class WebClientHandler extends ClientHandler {

	private WebDriver driver;
	private PageObjectManagerWeb pomWeb;
	private String windowName;
	private String browser;

	public WebClientHandler(String deviceName, WebDriver driver, DriverUtils driverUtils,
			String location, String browser) {
		super(deviceName,"", "Web", location);
		this.driver = driver;
		this.browser = browser;
		pomWeb = new PageObjectManagerWeb(driverUtils, deviceName);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public PageObjectManagerWeb getPageObjectManager() {
		return pomWeb;
	}

	public String getWindowName() {
		return windowName;
	}

	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	public DriverService getDriverService() {
		return WebBrowserType.valueOf(this.browser).getDriverService();
	}

}
