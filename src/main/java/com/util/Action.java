package com.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.drivers.DriverUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Action {

	private static final String SCREENSHOTS_FOLDER_NAME = System.getProperty("user.dir")+"\\reports\\screenshots";
	
	public Action() {
		// TODO Auto-generated constructor stub
	}

	public static void extentSetUp() {
		// TODO Auto-generated method stub
		
	}

	public static final Logger log = LogManager.getLogger(Action.class.getName());
	private WebDriver webDriver = null;
	private AppiumDriver<WebElement> AppiumDriver;
	public DriverUtils driverUtils;
	public SimpleDateFormat DATE_FORMATER_IMAGE_FOLDER = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	
	public WebDriver getWebDriver() {
		return webDriver;
	}

	public WebDriver getAppiumDriver() {
		return AppiumDriver;
	}
	
	public WebElement wait(WebDriver driver, WebElement element, int time) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofMillis(2000)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Add comment to report.
	 * @param comment
	 */
	public static void addComment(String comment) {
		log.info(comment);
		Reporter.addStepLog(comment);
	}

	
}
