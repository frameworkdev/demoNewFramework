package com.drivers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;

import com.constants.Constants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.util.Action;

import cucumber.api.Scenario;

public class DriverUtils extends Action{

	private static Scenario scenario;
	private JsonObject devicesJsonObject;
	private static final Logger log = LogManager.getLogger(DriverUtils.class.getName());

	private final Map<String, WebDriver> webDriverMap = new HashMap<>();
	private final Map<String, ClientHandler> handlers = new HashMap<>();
	private String device = Constants.DEVICE;
	private WebDriver webDriver;
	MutableCapabilities mutableCapabilities = new MutableCapabilities();
	

	public DriverUtils() throws IOException {
		readDeviceProperties();
	}
	
	private void readDeviceProperties() throws IOException {
		String fileData = new String(Files.readAllBytes(Paths.get(System.getProperty("devicesJsonPath"))));
		devicesJsonObject = new JsonParser().parse(fileData).getAsJsonObject();
	}
	
	public WebDriver getWebDriver(String aDevice) {
		return webDriverMap.get(aDevice);
	}

	public void setScenario(Scenario scenario) {
		DriverUtils.scenario = scenario;

	}

	public static Scenario getScenario() {
		return scenario;
	}

	public JsonObject getDevicesJsonObject() {
		return devicesJsonObject;
	}

	public void setDevicesJsonObject(JsonObject devicesJsonObject) {
		this.devicesJsonObject = devicesJsonObject;
	}

	public void initializeDrivers() throws Exception {
		System.out.println("Entered initilizeAllDeviceDrivers method in DriverUtils");
		try {
			System.out.println(device);
			
			JsonObject deviceDetails = devicesJsonObject.getAsJsonObject(device); // only for android and iOS
			
			if(device.equalsIgnoreCase("android")) {
				launchAndroidDrivers(deviceDetails);
			}
			else if(device.equalsIgnoreCase("iOS")) {
				launchIOSDrivers(deviceDetails);
			}else {
				System.out.println("In else");
				launchWebdrivers();
			}		
		} catch (Exception e) {
			log.info("Entered catch block of initializeDrivers()");
			e.printStackTrace();
			Assert.fail("Driver creation failed" + e.getMessage());
		}
	}

	private void launchIOSDrivers(JsonObject deviceDetails) {
		// TODO Auto-generated method stub
		
	}

	private void launchAndroidDrivers(JsonObject deviceDetails) {
		// TODO Auto-generated method stub
		
	}

	private void launchWebdrivers() {
		switch(device) {
		case "chrome":
			System.out.println("In chrome launch driver");
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
			webDriver = new ChromeDriver();
			// chrome related settings -- start
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
			// chrome related options -- end
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			webDriver.get(Constants.URL);
			
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH);
			webDriver = new FirefoxDriver();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addPreference("dom.webnotifications.enabled", true);
			firefoxOptions.addArguments("-enable-sync", "--start-maximized");
			mutableCapabilities.setCapability("moz:firefoxOptions", firefoxOptions);
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			webDriver.get(Constants.URL);
			
			break;
		}
		
	}

	public void closeDrivers(boolean isScenarioFailed) {

		try {
			//switch needs to be added
			webDriver.quit();
			log.info("WebDriver is closed");

		} catch (Exception e) {
			log.info("Exception in close drivers()");
		}
	}

	public WebClientHandler getWebClientHandler(String aDevice) {
		return (WebClientHandler) handlers.get(aDevice);
	}

}
