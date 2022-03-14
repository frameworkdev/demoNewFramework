package com.drivers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ClientHandler {

	private boolean isAndroid = false;
	private boolean isWeb = false;
	private boolean isIOS = false;
	private String model;
	private String location;
	private String deviceName;
	private static final Logger sLogger = LogManager.getLogger(ClientHandler.class.getName());

	public ClientHandler(String deviceName, String deviceType, String model, String location) {
		this.deviceName = deviceName;
		if (deviceType.equals("Android"))
			isAndroid = true;
		else if (deviceType.equals("Web"))
			isWeb = true;
		else if (deviceType.equals("iOS"))
			isIOS = true;
		this.model = model;
		this.location = location;
		sLogger.debug("Inside ClientHandler constructor");
	}

	public String getDeviceName() {
		sLogger.debug("Inside getDeviceName in ClientHandler");
		return deviceName;
	}

	public String getLocation() {
		sLogger.debug("Inside getLocation in ClientHandler");
		return location;
	}

	public String getModel() {
		return model;
	}

	public boolean isAndroid() {
		return isAndroid;
	}

	public boolean isWeb() {
		return isWeb;
	}

	public boolean isIOS() {
		return isIOS;
	}

}
