package com.StepDefinitions.mobile;

import cucumber.api.java.en.Given;

public class SampleMobile {

	@Given("^some other precondition$")
	public void some_other_precondition() throws Throwable {
	   System.out.println("In step def worked yo!");
	   Thread.sleep(10000);
	   System.out.println("Waited for 10 sec");
	}

}
