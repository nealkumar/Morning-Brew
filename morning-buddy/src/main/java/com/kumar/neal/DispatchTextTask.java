package com.kumar.neal;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class DispatchTextTask extends RetrievableTask<String>{
	
	private volatile String message;
	
	public DispatchTextTask(String message) {
		this.obj = "*** ERROR: Text message failed to send! ***";
		this.message = message;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Message.creator(new PhoneNumber(System.getenv("TWILIO_TO_PHONE")), // to
                new PhoneNumber(System.getenv("TWILIO_FROM_PHONE")), // from
                this.message)
        .create();
		this.obj = "Text message successfully dispatched.";
	}

}
