package com.kumar.neal;

import com.amazonaws.auth.AWSCredentials;
import com.kumar.neal.dispatch.Response;
import com.kumar.neal.dispatch.ResponseFactory;
import com.kumar.neal.dispatch.textmessage.DispatchSNSPublishTask;
import com.kumar.neal.initialization.BasicAWSCredentialsInitializationTask;
import com.kumar.neal.routecalculation.RouteCalculationTask;
import com.kumar.neal.weather.WeatherRemarksTask;

public class Driver{
	
	private Task routeCalculation, weatherRemarks, initializeAWSCreds, dispatchSNS;
	private Thread rct, wrt, dst, bacit;
	
	public static void main(String[] args) throws InterruptedException {
		new Driver().init();
	}
	
	/**
	 * Main thread orchestrator for the Morning Buddy agent.
	 * @throws InterruptedException
	 */
	public void init() throws InterruptedException {
		long start = System.currentTimeMillis();
		createTasks();
		createThreads();
		startThreads();
		
		Integer calc = this.routeCalculation.getVal();
		String remarks = this.weatherRemarks.getVal();
		AWSCredentials credentials = this.initializeAWSCreds.getVal();
		
		Response response = this.buildResponse(calc, remarks);
		String message = ResponseFactory.generateResponse(response);
		
		dispatchSNSText(message, credentials);
		long end = System.currentTimeMillis();
		System.out.println("Total execution time: " + (end-start));
	}
	
	private void createTasks() {
		this.routeCalculation = new RouteCalculationTask();
		this.weatherRemarks = new WeatherRemarksTask();
		this.initializeAWSCreds = new BasicAWSCredentialsInitializationTask();
	}
	
	private void createThreads() {
		this.rct = new Thread(this.routeCalculation);
		this.wrt = new Thread(this.weatherRemarks);
		this.bacit = new Thread(this.initializeAWSCreds);
	}
	
	private void startThreads() {
		this.rct.setPriority(10);
		this.rct.start();
		this.wrt.setPriority(10);
		this.wrt.start();
		this.bacit.setPriority(4);
		this.bacit.start();
	}
	
	private Response buildResponse(Integer calc, String remarks) {
		return new Response.ResponseBuilder()
				.withName("Neal")
				.withRouteCalculation(calc)
				.withWeatherRemarks(remarks)
				.build();
	}
	
	private void dispatchSNSText(String message, AWSCredentials credentials) {
		this.dispatchSNS = new DispatchSNSPublishTask(message, credentials);
		dst = new Thread(this.dispatchSNS);
		dst.start();
		return;
	}
}