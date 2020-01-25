package com.kumar.neal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public final class ApiController {
	
	@RequestMapping(method = RequestMethod.GET, value="/weather")
	@ResponseStatus(HttpStatus.OK)
	public String getWeather() {
		return WeatherFactory.generateWeatherRemarks();
	}
	
}
