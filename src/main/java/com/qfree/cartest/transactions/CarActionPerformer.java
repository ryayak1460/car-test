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
package com.qfree.cartest.transactions;

import com.qfree.cartest.entities.Car;
import com.qfree.cartest.entities.Components;
import com.qfree.cartest.entities.data.CarData;
import com.qfree.cartest.entities.data.Command;
import com.qfree.cartest.transactions.commands.HandleActionResponseCommand;
import com.qfree.cartest.transactions.data.Action;
import com.qfree.cartest.transactions.data.CarWithComponentsData;
import com.qfree.cartest.transactions.data.Component;
import com.qfree.cartest.transactions.data.Error;
import com.qfree.cartest.transactions.data.PerformCarActionRequest;
import com.qfree.cartest.transactions.data.PerformCarActionResponse;

public class CarActionPerformer {
    private Handler handler;

    public CarActionPerformer(Handler handler) {
        this.handler = handler;
    }

    public void process(PerformCarActionRequest request) {
        if (request.action == Action.NOTHING)
            doNothing(request);
        else
            tryToPerform(request);
    }

    private void doNothing(PerformCarActionRequest request) {
        PerformCarActionResponse response = new PerformCarActionResponse();
        response.car = request.car;
        handler.handle(response);
    }

    private void tryToPerform(PerformCarActionRequest request) {
        try {
            doPerform(request);
        } catch (Exception exception) {
            handleFailedRequest(exception.getMessage());
        }
    }

    private void doPerform(PerformCarActionRequest request) {
        Command command = new HandleActionResponseCommand(handler);
        Car car = makeCarFrom(request.car);
        Components component = makeCarComponentFrom(request.component);
        if (request.action == Action.TURN_ON)
            car.start(component);
        else
            car.turnOff(component);
        car.inspect(command);
    }

    private Car makeCarFrom(CarWithComponentsData car) {
        CarData data = new CarData();
        data.year = car.year;
        data.make = car.make;
        data.model = car.model;
        data.engine = car.engine;
        data.stereo = car.stereo;
        data.headlights = car.headlights;
        return new Car(data);
    }

    private Components makeCarComponentFrom(Component component) {
        return component == Component.HEADLIGHTS ?
                Components.HEADLIGHTS :
            component == Component.STEREO ?
                Components.STEREO :
                Components.ENGINE;
    }

    private void handleFailedRequest(String message) {
        PerformCarActionResponse response = new PerformCarActionResponse();
        response.error = new Error();
        response.error.message = message;
        handler.handle(response);
    }
}
