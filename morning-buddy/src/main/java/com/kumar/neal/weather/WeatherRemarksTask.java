package com.kumar.neal.weather;

import com.kumar.neal.RetrievableTask;

public class WeatherRemarksTask extends RetrievableTask<String>{

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		this.obj = "Snow Day Tomorrow!";
	}

}
