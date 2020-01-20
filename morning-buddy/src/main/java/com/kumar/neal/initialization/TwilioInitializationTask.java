package com.kumar.neal.initialization;

import com.kumar.neal.NonRetrievableTask;
import com.twilio.Twilio;

public class TwilioInitializationTask extends NonRetrievableTask{

	@Override
	protected void execute() {
		Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
		System.out.println("Twilio Initialized.");
	}

}
