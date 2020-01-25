package com.kumar.neal;

import com.github.cliftonlabs.json_simple.JsonObject;

public class WeatherFactory {
	
	public static String generateWeatherRemarks() {
		final JsonObject json = new JsonObject();
//		final JsonArray remarksArray = new JsonArray();
//		remarksArray.add("Warm clothes");
//		remarksArray.add("Waterproof outerwear");
//		remarksArray.add("Snow boots");
		String remarks = "It's cold out! And icy! Wear warm clothes...Don't forget gloooves!"; 
		json.put("remarks", remarks);
		return json.toJson();
	}

}
