package com.StepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.util.Actions;
import com.constants.*;

import com.drivers.DriverUtils;

import static com.Runner.CustomListener.SCENARIO_RESULT;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

public class Hooks {

	DriverUtils driverUtils;

	
	private static final Logger sLogger = LogManager.getLogger(Hooks.class.getName());

	public Hooks(DriverUtils aDriverUtils) {
		this.driverUtils = aDriverUtils;
	}
	
	public static long startTime = System.currentTimeMillis();
	
	@Before
	public void init(Scenario scenario) {
		sLogger.info("Entered init method");
		sLogger.info("Start time is:"+startTime);
		driverUtils.setScenario(scenario);
		Actions.extentSetUp();
		sLogger.info("#######################" + repeat("#", scenario.getName().length()) + "#######################");
		sLogger.info("###########  START OF SCENARIO : {}", scenario.getName() + "  ###########");
		sLogger.info("#######################" + repeat("#", scenario.getName().length()) + "#######################");
	}

	@After
	public void afterScenario(Scenario scenario) {

		String feature = capitalize(scenario.getId().split(";")[0]);
		SCENARIO_RESULT.put(feature+"@"+scenario.getName(), capitalize(scenario.getStatus()));
		boolean isScenarioFailed = (scenario.getStatus().equalsIgnoreCase("Failed"))? true : false;
		System.out.println("isScenarioFailed------------------------"+isScenarioFailed);
		driverUtils.closeDrivers(isScenarioFailed);
		sLogger.info("##########################" + repeat("#", scenario.getName().length() + scenario.getStatus().length() ) + "###########################");
		sLogger.info("### END OF SCENARIO : {}, Time taken:{}, Status:{} ###", scenario.getName(), formatDuration(Constants.sysTime - startTime, "HH:mm:ss"), capitalize(scenario.getStatus()));
		sLogger.info("##########################" + repeat("#", scenario.getName().length() + scenario.getStatus().length() ) + "###########################");

	}
}
