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
package com.qfree.cartest.transactions.commands;

import com.qfree.cartest.entities.data.CarData;
import com.qfree.cartest.entities.data.Command;
import com.qfree.cartest.entities.data.Data;
import com.qfree.cartest.transactions.Handler;
import com.qfree.cartest.transactions.data.CarWithComponentsData;
import com.qfree.cartest.transactions.data.PerformCarActionResponse;

public class HandleActionResponseCommand implements Command {
    private Handler handler;

    public HandleActionResponseCommand(Handler handler) {
        this.handler = handler;
    }

    public void execute(Data data) {
        CarData car = (CarData)data;
        PerformCarActionResponse response = new PerformCarActionResponse();
        response.car = new CarWithComponentsData();
        response.car.year = car.year;
        response.car.make = car.make;
        response.car.model = car.model;
        response.car.engine = car.engine;
        response.car.headlights = car.headlights;
        response.car.stereo = car.stereo;
        this.handler.handle(response);
    }
}
