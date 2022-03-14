package com.StepDefinitions.mobile;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.constants.Constants;
import com.drivers.DriverUtils;
import com.pages.PageObjectManagerWeb;
import com.util.Action;
import com.util.WebDriverActions;

import cucumber.api.java.en.Given;

public class SampleMobile extends Action{
	private int count = 0;
	private DriverUtils driverUtils;
	private PageObjectManagerWeb pomWeb;

	private static final Logger log = LogManager.getLogger(SampleMobile.class.getName());

	
	public SampleMobile(DriverUtils aDriverUtils) {
		driverUtils = aDriverUtils;
		pomWeb=new PageObjectManagerWeb(driverUtils, Constants.DEVICE);
	}
	
	@Given("^Fetch data from API$")
	public void fetch_data_from_API() throws Throwable {
	   try{
		   if(count>=1) {
			   System.out.println("rest api data get");
		   }
		   
		   
	   }catch(Exception e) {
		   e.getMessage();
	   }
	}
	
	@Given("^launch browser$")
	public void launch_browser() throws Throwable {
		try {
			
		log.info("In launch browser");
		driverUtils.initializeDrivers();
		addCommentAndScreenshot_Web("web page is opened");
		}
		catch(Exception e) {
			log.info(e.getMessage());
		}
	}

	
}
