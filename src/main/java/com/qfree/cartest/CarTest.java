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
         *       print out a message indicating each component has turned on or off
         *              e.x. "Engine has started"
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
