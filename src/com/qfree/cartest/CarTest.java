package com.qfree.cartest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.qfree.cartest.models.ICar;

public class CarTest {
	
	private final List<ICar> carLot;
	
	CarTest() {
		carLot = new ArrayList<>();
	}
	
	public static void main(String args[]) {
		System.out.println("App started!");
		
		CarTest carTest = new CarTest();
		/**
		 * Step 1:
		 * 
		 * Create two(2) or more car implementations and add those
		 * cars to the car lot.  After building out the lot, print the inventory
		 * of the lot utilizing Java 8 streams to include all relevant attributes 
		 * of the vehicles (make/model/year).  At least 1 of the new cars must 
		 * be model year 2016 or later
		 */
		
		
		/**
		 * Step 2:
		 * 
		 * Create a filter predicate to only print out new cars and print those cars
		 */
		
		
		/**
		 * Step 3:
		 * 
		 * - Create Engine / Headlights / Stereo components
		 * - Modify the vehicle creation to pass in instances of each component for each car 
		 * - Finish building out the turn-on/turn-off function to send a command to each component and 
		 * 	 print out a message indicating each component has turned on or off 
		 * 		e.x. "Engine has started"
		 * - Make sure to add error and exception handling if attempting to start a vehicle that's
		 *   already started or stop one that's already stopped.
		 * - Invoke the turn on and turn off method for each car in the lot, printing all
		 *   status to the console
		 */		
	}
	
	void buildCarLot() {
		//TODO: build car lot 
	}
	
	void printLotInventory() {
		//TODO: print lot inventory
	}
	
	void printNewCars(Predicate<ICar> predicate) {
		// TODO: print only cars with model years 2016 and newer
	}
	
	void startAllCars() {
		// TODO: start all the cars in the lot
	}
	
	void stopAllCars() {
		// TODO: turn off all cars in the lot
	}
	
}
