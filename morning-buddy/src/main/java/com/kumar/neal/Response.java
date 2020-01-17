package com.kumar.neal;

public class Response {
	
	private String name, weatherRemarks;
	private int routeCalc; 
	
	private Response(ResponseBuilder rb) {
		super();
	}
	
	/**
	 * Builds the Response object.
	 * @author nealk
	 *
	 */
	public static class ResponseBuilder{
		private String name, weatherRemarks;
		private int routeCalc;
		
		public ResponseBuilder() {
			super();
		}
		
		public ResponseBuilder withName(String name) {
			this.name = name;
			return this;
		}
		
		public ResponseBuilder withWeatherRemarks(String weatherRemarks) {
			this.weatherRemarks = weatherRemarks;
			return this;
		}
		
		public ResponseBuilder withRouteCalculation(int routeCalc) {
			this.routeCalc = routeCalc;
			return this;
		}
		
		public Response build() {
			Response response = new Response(this);
			response.name = this.name;
			response.routeCalc = this.routeCalc;
			response.weatherRemarks = this.weatherRemarks;
			System.out.println("Response Built!");
			return response;
		}
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the weatherRemarks
	 */
	public String getWeatherRemarks() {
		return weatherRemarks;
	}

	/**
	 * @return the routeCalc
	 */
	public int getRouteCalc() {
		return routeCalc;
	}

}
