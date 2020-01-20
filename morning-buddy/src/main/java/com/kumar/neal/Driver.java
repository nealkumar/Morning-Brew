package com.kumar.neal;

import com.amazonaws.auth.BasicAWSCredentials;
import com.kumar.neal.dispatch.textmessage.DispatchSNSTask;
import com.kumar.neal.dispatch.textmessage.response.Response;
import com.kumar.neal.dispatch.textmessage.response.ResponseFactory;
import com.kumar.neal.initialization.BasicAWSCredentialsInitializationTask;
import com.kumar.neal.routecalculation.RouteCalculationTask;
import com.kumar.neal.weather.WeatherRemarksTask;

public class Driver{
	
	private Task routeCalculation, weatherRemarks, initializeAWSCreds, dispatchSNS;
	private Thread rct, wrt, dst, bacit;
	
	public static void main(String[] args) throws InterruptedException {
		new Driver().init();
	}
	
	public void init() throws InterruptedException {
		this.routeCalculation = new RouteCalculationTask();
		this.weatherRemarks = new WeatherRemarksTask();
		this.initializeAWSCreds = new BasicAWSCredentialsInitializationTask();
		
		this.rct = new Thread(this.routeCalculation);
		this.wrt = new Thread(this.weatherRemarks);
		this.bacit = new Thread(this.initializeAWSCreds);
		
		this.rct.setPriority(10);
		this.rct.start();
		this.wrt.setPriority(10);
		this.wrt.start();
		this.bacit.setPriority(4);
		this.bacit.start();
		
		Integer calc = this.routeCalculation.getVal();
		String remarks = this.weatherRemarks.getVal();
		BasicAWSCredentials credentials = this.initializeAWSCreds.getVal();
		
		Response response = this.buildResponse(calc, remarks);
		String message = ResponseFactory.generateResponse(response);
		
		//this.dispatchText = new DispatchTextTask(message);
		//dtt = new Thread(this.dispatchText);
		//dtt.start();
		//System.out.println("Message status: " + this.dispatchText.getVal());
		
		this.dispatchSNS = new DispatchSNSTask(message, credentials);
		dst = new Thread(this.dispatchSNS);
		dst.start();
		return;
	}
	
	private Response buildResponse(Integer calc, String remarks) {
		return new Response.ResponseBuilder()
				.withName("Neal")
				.withRouteCalculation(calc)
				.withWeatherRemarks(remarks)
				.build();
	}

}
