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
    private PerformCarActionResponse response;

    public CarActionPerformer(Handler handler) {
        this.handler = handler;
    }

    public void process(PerformCarActionRequest request) {
        makeResponse(request);
        if (willDoNothing())
            handler.handle(response);
        else
            tryToPerform();
    }

    private void makeResponse(PerformCarActionRequest request) {
        this.response = new PerformCarActionResponse();
        response.action = request.action;
        response.component = request.component;
        response.car = request.car;
    }

    private boolean willDoNothing() {
        return response.action == Action.NOTHING;
    }

    private void tryToPerform() {
        try {
            doPerform();
        } catch (Exception exception) {
            handleFailedRequest(exception.getMessage());
        }
    }

    private void doPerform() {
        Car car = makeCar();
        Components component = makeCarComponent();
        if (willTurnOn())
            car.start(component);
        else
            car.turnOff(component);
        handleSuccessfulRequest(car);
    }

    private Car makeCar() {
        CarWithComponentsData car = response.car;
        CarData data = new CarData();
        data.year = car.year;
        data.make = car.make;
        data.model = car.model;
        data.engine = car.engine;
        data.stereo = car.stereo;
        data.headlights = car.headlights;
        return new Car(data);
    }

    private Components makeCarComponent() {
        return response.component == Component.HEADLIGHTS ?
                Components.HEADLIGHTS :
            response.component == Component.STEREO ?
                Components.STEREO :
                Components.ENGINE;
    }

    private boolean willTurnOn() {
        return response.action == Action.TURN_ON;
    }

    private void handleSuccessfulRequest(Car car) {
        Command command = new HandleActionResponseCommand(handler, response);
        car.inspect(command);
    }

    private void handleFailedRequest(String message) {
        response.error = new Error();
        response.error.message = message;
        handler.handle(response);
    }
}
