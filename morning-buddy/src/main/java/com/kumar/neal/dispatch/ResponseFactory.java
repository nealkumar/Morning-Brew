package com.kumar.neal.dispatch;

public class ResponseFactory {

	private static StringBuilder sb;
	
	public static String generateResponse(Response response) {
		System.out.println("Processing Response...");
		sb = new StringBuilder();
		sb.append("Hello, ");
		sb.append(response.getName());
		sb.append("!\n\nCurrently, it will take ");
		sb.append(response.getRouteCalc() + " minutes to reach school.");
		sb.append("\n\nAdditionally, here are your weather remarks: ");
		sb.append(response.getWeatherRemarks());
		return sb.toString();
	}

}
