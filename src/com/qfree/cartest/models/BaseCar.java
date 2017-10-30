package com.qfree.cartest.models;

import java.util.ArrayList;
import java.util.List;

import com.qfree.cartest.components.IComponent;

public abstract class BaseCar implements ICar {

	private List<IComponent> components = new ArrayList<>();
	
	protected BaseCar(IComponent... components) {
		//TODO: build components of the car
	}
	
	@Override
	public boolean start() {
		// TODO: turn on all components
		System.out.println("Started car");
		return true;
	}

	@Override
	public boolean turnOff() {
		//TODO: turn off all components
		System.out.println("Stopped car");
		return true;
	}
	
	
	
}
