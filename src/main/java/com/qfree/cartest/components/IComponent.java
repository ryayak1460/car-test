package com.qfree.cartest.components;

import com.qfree.cartest.actions.CarCommands;

public interface IComponent {

	public void accept(CarCommands command);
		
}
