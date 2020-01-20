package com.kumar.neal.dispatch.textmessage;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.kumar.neal.NonRetrievableTask;
import com.kumar.neal.RetrievableTask;
import com.kumar.neal.Task;

public class DispatchSNSPublishTask extends NonRetrievableTask {

	private String message;
	private AWSCredentials credentials;
	private Task snsBuilderTask, publishRequestBuilderTask;
	private Thread sbt, prbt;

	/**
	 * Dispatches a text message using Amazon Simple Notification Service.
	 * @param message - message content to send
	 * @param credentials - AWS Credentials
	 */
	public DispatchSNSPublishTask(String message, AWSCredentials credentials) {
		super();
		this.credentials = credentials;
		this.message = message;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		this.snsBuilderTask = new SNSBuilderTask();
		this.sbt = new Thread(this.snsBuilderTask);
		this.publishRequestBuilderTask = new PublishRequestBuilderTask();
		this.prbt = new Thread(this.publishRequestBuilderTask);
		this.prbt.start();
		this.sbt.start();
		
		PublishRequest request = this.getPublishRequest(this.publishRequestBuilderTask);
		AmazonSNS snsClient = this.getAmazonSns(this.snsBuilderTask);

		PublishResult result = snsClient.publish(request);
		System.out.println("Text message successfully dispatched. ID = " + result.getMessageId());
	}
	
	private AmazonSNS getAmazonSns(Task t) {
		try {
			return t.getVal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Amazon SNS issue... See: ~/dispatch.textmessage.DispatchSNSPublishTask.java");
		return AmazonSNSClientBuilder.defaultClient();
	}
	
	private PublishRequest getPublishRequest(Task t) {
		try {
			return t.getVal();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Publish Request issue... See: ~/dispatch.textmessage.DispatchSNSPublishTask.java");
		return new PublishRequest();
	}
	
	private class SNSBuilderTask extends RetrievableTask<AmazonSNS>{
		@Override
		protected void execute() {
			this.obj = AmazonSNSClientBuilder.standard()
					.withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials))
					.build();
		}
	}
	
	private class PublishRequestBuilderTask extends RetrievableTask<PublishRequest>{
		@Override
		protected void execute() {
			this.obj = new PublishRequest()
					   .withMessage(message)
					   .withPhoneNumber(System.getenv("TWILIO_TO_PHONE"));
		}
	}
}