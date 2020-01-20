package com.kumar.neal.initialization;

import com.amazonaws.auth.BasicAWSCredentials;
import com.kumar.neal.RetrievableTask;

public class BasicAWSCredentialsInitializationTask extends RetrievableTask<BasicAWSCredentials>{
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		this.obj = new BasicAWSCredentials(System.getenv("AWS_ACCESS_KEY_ID"),
					System.getenv("AWS_SECRET_ACCESS_KEY"));
	}

}
