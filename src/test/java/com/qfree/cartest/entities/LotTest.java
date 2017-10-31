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
package com.qfree.cartest.entities;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.qfree.cartest.entities.data.CarData;
import com.qfree.cartest.entities.data.LotData;
import com.qfree.cartest.helpers.LotCommand;

public class LotTest {
    private Lot lot;
    private Car car;
    private LotCommand command;

    @Before
    public void housekeeping() {
        CarData carData = new CarData();
        carData.year = 2013;
        carData.make = "Ford";
        carData.model = "Focus";
        LotData data = new LotData();
        this.car = new Car(carData);
        this.lot = new Lot(data);
        this.command = new LotCommand();
    }

    @Test
    public void testAddCarIncludesCorrectCarMake() {
        this.lot.add(this.car);
        this.lot.inspect(this.command);
        assertThat(this.getFirst().make, equalTo("Ford"));
    }

    private CarData getFirst() {
        return this.command.data.cars.get(0);
    }

    @Test
    public void testAddCarIncludesCorrectCarModel() {
        this.lot.add(this.car);
        this.lot.inspect(this.command);
        assertThat(this.getFirst().model, equalTo("Focus"));
    }

    @Test
    public void testAddCarIncludesCorrectCarYear() {
        this.lot.add(this.car);
        this.lot.inspect(this.command);
        assertThat(this.getFirst().year, equalTo(2013));
    }
}
