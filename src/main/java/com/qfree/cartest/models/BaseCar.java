/*
 *  Car Test, a test program to see how I handle Java 8.
 *  Copyright (C) 2017  Ryan Y.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
