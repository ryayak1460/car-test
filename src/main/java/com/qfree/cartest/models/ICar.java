package com.qfree.cartest.models;

import com.qfree.cartest.actions.CarActions;

public interface ICar extends CarActions {

	/**
	 * @return the make of the car
	 */
	String getCarMake();
	
	/**
	 * @return the model of the car
	 */
	String getCarModel();
	
	/**
	 * @return the year the car was manufactured
	 */
	String getCarYear();
	
}
