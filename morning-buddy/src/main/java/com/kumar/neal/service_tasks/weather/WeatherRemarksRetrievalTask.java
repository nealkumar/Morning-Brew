package com.kumar.neal.service_tasks.weather;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kumar.neal.RetrievableTask;
import com.kumar.neal.service_tasks.ServiceTasksConstants;

public class WeatherRemarksRetrievalTask extends RetrievableTask<String>{
	@Override
	protected void execute() {
		String remarks = "None.";
		try {
			URL url = new URL(ServiceTasksConstants.WEATHER_SERVICE_URI);
			URLConnection request = url.openConnection();
			request.connect();
			//Convert to JSON object to return data
			@SuppressWarnings("deprecation")
			JsonParser parser = new JsonParser();
			@SuppressWarnings("deprecation")
			JsonElement root = parser.parse(new InputStreamReader((InputStream) request.getContent()));  //Converts input stream to JSON elemnt
			JsonObject rootObj = root.getAsJsonObject();
			remarks = rootObj.get("remarks").getAsString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException ioex) {
			ioex.printStackTrace();
		}
		this.obj = remarks;
	}
}
