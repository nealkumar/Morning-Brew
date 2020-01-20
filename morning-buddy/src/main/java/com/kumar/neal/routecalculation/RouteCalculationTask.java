package com.kumar.neal.routecalculation;

import com.kumar.neal.RetrievableTask;

/**
 * <p>Calculates the number of minutes to get to work and returns it as
 * an Integer object.</p>
 * @author nealk
 *
 */
public class RouteCalculationTask extends RetrievableTask<Integer>{
	
	@Override
	protected void execute() {
		this.obj = Integer.MAX_VALUE;
	}
	
}