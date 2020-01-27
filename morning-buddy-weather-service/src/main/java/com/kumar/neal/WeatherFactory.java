package com.kumar.neal;

import com.github.cliftonlabs.json_simple.JsonObject;

public class WeatherFactory {
	
	private enum SummaryTypes{
		minutely, hourly;
	}
	
	public static String generateWeatherRemarks() {
		final JsonObject json = new JsonObject();
		String remarks = "It's cold out! And icy! Wear warm clothes...Don't forget gloooves!";
		//String minSummary = 
		json.put("remarks", remarks);
		return json.toJson();
	}

}
