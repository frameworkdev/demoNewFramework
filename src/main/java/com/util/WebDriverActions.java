package com.util;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.drivers.DriverUtils;

public class WebDriverActions extends Action{

	public WebDriverActions(DriverUtils driverUtils) {
		// TODO Auto-generated constructor stub
	}
	private static final Logger log = LogManager.getLogger(WebDriverActions.class.getName());
	private static final String SCREENSHOTS_FOLDER_NAME = System.getProperty("user.dir")+"\\reports\\screenshots\\";
	public DriverUtils driverUtils;
	private String device;
	
	public WebDriverActions(DriverUtils aDriverUtils, String aDevice) {
		driverUtils = aDriverUtils;
		device = aDevice;
	}
	
	public WebDriver getWebDriver() {
		return driverUtils.getWebDriver(device);
	}

	/**
	 * Waits for an element to be loaded and visible for certain time
	 * @param element , time in seconds
	 * @return null
	 */
	public void waitForElement(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * Waits for an element to be loaded and ready to perform click action
	 * @param element, time in seconds
	 * @return this
	 */
	
	public WebDriverActions waitForClickable(WebElement element, long timeoutInSeconds ) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), timeoutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return this;
	}

	/**
	 * Waits for an element to be loaded and visible
	 * @param element
	 * @return element
	 */
	public WebElement waitForElement(WebElement element) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(getWebDriver()).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofMillis(2000)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Waits for element to be loaded and then Clicks on it.	 
	 * @param element Element on which action should be performed
	 * @param comments Name of the element (Appends this name in addCommentAndScreenshot())
	 * @return performed action
	 */
	public Action clickWebElement(WebElement element,String context) {
		try{
			waitForElement(element).click();
			addCommentAndScreenshot_Web(context+" is clicked");
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			addCommentAndScreenshot_Web(context+ "is not clicked");
			Assert.fail(context+ "is not clicked", e);
		}
		return this;
	}

	/**
	 * Checks if element is enabled or not	 
	 * @param element Element on which action should be performed
	 * @return boolean value
	 */
	public boolean clickWebElement(WebElement element) throws Exception {

		try {
			return  element.isEnabled() ? true : false; 
			
		}catch (Exception e) {
			Assert.fail();
			throw e;
		}
	}

	/**
	 * Double Clicks on an element.	 
	 * @param element Element on which action should be performed
	 * @return null
	 */
	public void doubleClickWebelement(WebElement doubleclickonWebElement) throws InterruptedException {
		Actions builder = new Actions(getWebDriver());
		builder.doubleClick(doubleclickonWebElement).perform();
		Thread.sleep(2000);
	}
	
	public void addCommentAndScreenshot_Web(String comment){
		try {
			addComment(comment + " [Screenshot]");
			Reporter.addScreenCaptureFromPath(takeScreenshot_Web());
			log.debug(comment);
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			Assert.fail("Failed in addCommentAndScreenshot()"+e.getMessage(), e);
		}	
	}



	public String takeScreenshot_Web()
	{
		String screenshotDest=null;

		try {

			File srcFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE); 
			File screenshotsDir = new File(SCREENSHOTS_FOLDER_NAME + DriverUtils.getScenario().getName());			
			if(!screenshotsDir.exists())
				screenshotsDir.mkdirs();

			screenshotDest = screenshotsDir.getPath() + "/" +  DATE_FORMATER_IMAGE_FOLDER.format(System.currentTimeMillis()) + ".png";
			File destFile = new File(screenshotDest);
			FileUtils.copyFile(srcFile, destFile);

		} catch (IOException e) {
			Reporter.addStepLog("Failed in takeScreenshot");
			e.printStackTrace();
		}return screenshotDest;
	}




	public void switchToOldWindow() {
		
		Set<String> s = getWebDriver().getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = itr.next();
		String w2 = itr.next();
		log.debug("Switching to old window : "+w1+" from window : "+w2);
		getWebDriver().switchTo().window(w1);
	}
	
	public void switchToNewWindow() {
		
		Set<String> s = getWebDriver().getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = itr.next();
		String w2 = itr.next();
		log.debug("Switching to new window : "+w1+" from window : "+w2);
		getWebDriver().switchTo().window(w2);
		
	}
	
	public void switchWindowByTitle(String title) {

		String currentWindow = getWebDriver().getWindowHandle();
		Set<String> availableWindows = getWebDriver().getWindowHandles();
		System.out.println("WindowSize: " + availableWindows.size());
		for (String windowId : getWebDriver().getWindowHandles()) {
			System.out.println("Windows: " + getWebDriver().switchTo().window(windowId).getTitle());
			if(!getWebDriver().switchTo().window(windowId).getTitle().contains(title)) {
				System.out.println("Wrong.....");
				getWebDriver().switchTo().window(currentWindow);
			}
		}
	}

	public void switchToFrame_byWebElement(WebElement element) throws Exception {
		
		try {
			getWebDriver().switchTo().frame(element);
		} catch (Exception e) {
			
			throw e;
		}
	}

}
