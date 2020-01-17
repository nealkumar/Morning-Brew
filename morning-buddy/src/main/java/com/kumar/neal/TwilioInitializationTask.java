package com.kumar.neal;

import com.twilio.Twilio;

public class TwilioInitializationTask extends NonRetrievableTask{

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
		System.out.println("Twilio Initialized.");
	}

}
