package com.commonMethods;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.drivers.DriverUtils;
import com.util.WebDriverActions;

public class StringOperationMethods extends WebDriverActions{

		private static final Logger log = LogManager.getLogger(StringOperationMethods.class.getName());
		
		public StringOperationMethods(DriverUtils aDriverUtils, String aDevice) {
			super(aDriverUtils, aDevice);
			PageFactory.initElements(driverUtils.getWebDriver(aDevice), this);
			
			
		}
		
		public String splitStringFromText(String text) {
			
			text = text.replace("\n",",");
			String[] splitString = text.split(" ", 20);
			ArrayList<String> textList = new ArrayList<>();
			for(String s : splitString) {
				textList.add(s);
			}
			System.out.println(text);
			text = textList.get(1);
			splitString = text.split(",");
			text = splitString[0];
			
			return text;
		}

}
