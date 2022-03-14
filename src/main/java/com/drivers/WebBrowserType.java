package com.drivers;

import com.constants.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.service.DriverService;

public enum WebBrowserType {

	CHROME ("org.openqa.selenium.chrome.ChromeDriver", "webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH),
	FIREFOX("", "webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);

	private String driverClass;
	private String driverPathProp; 
	private String driverPath;
	private DriverService driverService;

	WebBrowserType(String aClass, String aDriverPathProp, String aDriverPath) {
		driverClass = aClass;
		driverPathProp = aDriverPathProp;
		driverPath = aDriverPath;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getDriverPathProp() {
		return driverPathProp;
	}

	public String getDriverPath() {
		return driverPath;
	}

	public MutableCapabilities getBrowserOptions() {

		MutableCapabilities mutableCapabilities = new MutableCapabilities();

		switch (this) {
			case CHROME:
				System.out.println("I'm here............");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--use-fake-ui-for-media-stream");
				//Create a map to store  preferences
				Map<String, Object> prefs = new HashMap<>();
				//Argument 1 : allow and 2 : block
				prefs.put("profile.default_content_setting_values.notifications", 1);

				ArrayList<String> options = new ArrayList<String>();
				options.add("disable-sync");
				chromeOptions.setExperimentalOption("excludeSwitches", options);
				chromeOptions.setExperimentalOption("prefs", prefs);
				chromeOptions.addArguments("-enable-sync", "--start-maximized");
				mutableCapabilities.setCapability("chromeOptions", chromeOptions);
				break;
			case FIREFOX:
				// Never tested. May be we need to add more options
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addPreference("dom.webnotifications.enabled", true);
				firefoxOptions.addArguments("-enable-sync", "--start-maximized");
				mutableCapabilities.setCapability("moz:firefoxOptions", firefoxOptions);
				break;
		}
		return mutableCapabilities;
	}

	public DriverService getDriverService() {

		switch (this) {
			case CHROME:
				if (this.driverService == null)
					this.driverService = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverPath)).usingAnyFreePort().build();
				break;

			case FIREFOX:
				if (this.driverService == null)
					this.driverService = new GeckoDriverService.Builder().usingDriverExecutable(new File(driverPath)).usingAnyFreePort().build();
				break;
		}
		return this.driverService;
	}
}
