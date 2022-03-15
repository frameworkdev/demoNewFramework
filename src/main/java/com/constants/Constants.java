package com.constants;

import java.sql.Date;

public class Constants {
	public static final String DEVICE = System.getProperty("device");
	public static final String SYSDATE = new Date(System.currentTimeMillis()).toString();
	public static final String REPORT_FORMAT = "com.cucumber.listener.ExtentCucumberFormatter:reports/"+DEVICE+"/"+SYSDATE+"Report.html";
    
	public static final String URL = System.getProperty("url");
	public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\Webdrivers\\chromedriver.exe";
	public static final String FIREFOX_DRIVER_PATH = System.getProperty("user.dir") + "/src/test/resources/Webdrivers/";
	
}
