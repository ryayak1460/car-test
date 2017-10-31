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
package com.qfree.cartest.entities.commands;

import com.qfree.cartest.entities.data.CarData;
import com.qfree.cartest.entities.data.Command;
import com.qfree.cartest.entities.data.Data;
import com.qfree.cartest.entities.data.LotData;

public class AddCarCommand implements Command {
    private LotData data;

    public AddCarCommand(LotData data) {
        this.data = data;
    }

    public void execute(Data data) {
        CarData car = (CarData)data;
        this.data.cars.add(car);
    }
}
