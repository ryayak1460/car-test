/*
 * Copyright 2017 Ryan Y.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
