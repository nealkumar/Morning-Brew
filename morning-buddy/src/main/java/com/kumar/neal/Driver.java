package com.kumar.neal;

public class Driver {
	
	private Task routeCalculation, weatherRemarks, twilioInit, dispatchText;
	private Thread rct, wrt, dtt;
	
	public static void main(String[] args) throws InterruptedException {
		new Driver().init();
	}
	
	public void init() throws InterruptedException {
		this.routeCalculation = new RouteCalculationTask();
		this.weatherRemarks = new WeatherRemarksTask();
		this.twilioInit = new TwilioInitializationTask();
		this.rct = new Thread(this.routeCalculation);
		this.wrt = new Thread(this.weatherRemarks);
		
		this.rct.start();
		this.wrt.start();
		new Thread(this.twilioInit).start();
		
		Integer calc = this.routeCalculation.getVal();
		String remarks = this.weatherRemarks.getVal();
		
		Response response = this.buildResponse(calc, remarks);
		String message = ResponseFactory.generateResponse(response);
		this.dispatchText = new DispatchTextTask(message);
		dtt = new Thread(this.dispatchText);
		dtt.start();
		System.out.println("Message status: " + this.dispatchText.getVal());
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
