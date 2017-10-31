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

import com.qfree.cartest.entities.data.CarData;
import com.qfree.cartest.entities.data.Command;
import com.qfree.cartest.entities.data.Data;
import com.qfree.cartest.entities.errors.EngineAlreadyOff;
import com.qfree.cartest.entities.errors.EngineAlreadyStarted;
import com.qfree.cartest.helpers.CarCommand;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CarTest {
    private CarData data;
    private CarCommand command;

    @Before
    public void housekeeping() {
        this.data = new CarData();
        this.data.make = "Ford";
        this.data.model = "Focus";
        this.data.year = 2013;
        this.command = new CarCommand();
    }

    @Test
    public void testStartChangesStateToStarted() {
        Car car = new Car(this.data);
        car.start();
        car.inspect(this.command);
        assertThat(this.command.data.engine, equalTo(true));
    }

    @Test
    public void testStartDoesNotUpdateOriginalReference() {
        Car car = new Car(this.data);
        car.start();
        car.inspect(this.command);
        assertThat(this.data.engine, equalTo(false));
    }

    @Test(expected = EngineAlreadyStarted.class)
    public void testStartThrowsWhenAlreadyStarted() {
        this.data.engine = true;
        Car car = new Car(this.data);
        car.start();
    }

    @Test
    public void testTurnOffChangesEngineToOff() {
        this.data.engine = true;
        Car car = new Car(this.data);
        car.turnOff();
        car.inspect(this.command);
        assertThat(this.command.data.engine, equalTo(false));
    }

    @Test
    public void testTurnOffDoesNotUpdateOriginalReference() {
        this.data.engine = true;
        Car car = new Car(this.data);
        car.turnOff();
        car.inspect(this.command);
        assertThat(this.data.engine, equalTo(true));
    }

    @Test(expected = EngineAlreadyOff.class)
    public void testTurnOffThrowsWhenAlreadyOff() {
        Car car = new Car(this.data);
        car.turnOff();
    }
}
