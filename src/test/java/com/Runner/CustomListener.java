package com.Runner;

import java.util.*;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

public class CustomListener extends TestListenerAdapter {

	public static final Map<String, String> SCENARIO_RESULT = new TreeMap<>();

	@Override
	public void onFinish(ITestContext context) {
		super.onFinish(context);
		try {
			Properties properties = new Properties();
			properties.putAll(SCENARIO_RESULT);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
