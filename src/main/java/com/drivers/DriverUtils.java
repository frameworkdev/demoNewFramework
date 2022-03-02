package com.drivers;

import java.util.Properties;


import com.google.gson.JsonObject;
import cucumber.api.Scenario;

public class DriverUtils {
	
	private static Scenario scenario;
	private JsonObject devicesJsonObject;
	private static Properties properties;

	
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


	public static Properties getProperties() {
		return properties;
	}


	public static void setProperties(Properties properties) {
		DriverUtils.properties = properties;
	}


	public void closeDrivers(boolean isScenarioFailed) {
		// TODO Auto-generated method stub
		
	}

}
