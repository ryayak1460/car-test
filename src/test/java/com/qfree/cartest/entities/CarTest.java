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

import com.qfree.cartest.entities.data.CarData;
import com.qfree.cartest.entities.data.Command;
import com.qfree.cartest.entities.data.Data;
import com.qfree.cartest.entities.errors.EngineAlreadyOff;
import com.qfree.cartest.entities.errors.EngineAlreadyStarted;
import com.qfree.cartest.entities.errors.EngineOff;
import com.qfree.cartest.entities.errors.HeadlightsAlreadyOff;
import com.qfree.cartest.entities.errors.HeadlightsAlreadyOn;
import com.qfree.cartest.entities.errors.StereoAlreadyOff;
import com.qfree.cartest.entities.errors.StereoAlreadyOn;
import com.qfree.cartest.helpers.CarCommand;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CarTest {
    private CarData data;
    private CarCommand command;
    private Car car;

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
        this.car = new Car(this.data);
        this.car.start();
        this.car.inspect(this.command);
        assertThat(this.command.data.engine, equalTo(true));
    }

    @Test
    public void testStartDoesNotUpdateOriginalReference() {
        this.car = new Car(this.data);
        this.car.start();
        this.car.inspect(this.command);
        assertThat(this.data.engine, equalTo(false));
    }

    @Test(expected = EngineAlreadyStarted.class)
    public void testStartThrowsWhenAlreadyStarted() {
        this.data.engine = true;
        this.car = new Car(this.data);
        this.car.start();
    }

    @Test
    public void testTurnOffChangesEngineToOff() {
        this.data.engine = true;
        this.car = new Car(this.data);
        this.car.turnOff();
        this.car.inspect(this.command);
        assertThat(this.command.data.engine, equalTo(false));
    }

    @Test
    public void testNewUsesHeadlightsValue() {
        this.data.headlights = true;
        this.car = new Car(this.data);
        this.car.inspect(this.command);
        assertThat(this.command.data.headlights, equalTo(true));
    }

    @Test
    public void testNewUsesStereoValue() {
        this.data.stereo = true;
        this.car = new Car(this.data);
        this.car.inspect(this.command);
        assertThat(this.command.data.stereo, equalTo(true));
    }

    @Test
    public void testTurnOffChangesHeadlightsToOff() {
        this.data.engine = true;
        this.data.headlights = true;
        this.car = new Car(this.data);
        this.car.turnOff();
        this.car.inspect(this.command);
        assertThat(this.command.data.headlights, equalTo(false));
    }

    @Test
    public void testTurnOffChangesStereoToOff() {
        this.data.engine = true;
        this.data.stereo = true;
        this.car = new Car(this.data);
        this.car.turnOff();
        this.car.inspect(this.command);
        assertThat(this.command.data.stereo, equalTo(false));
    }

    @Test
    public void testTurnOffDoesNotUpdateOriginalReference() {
        this.data.engine = true;
        this.car = new Car(this.data);
        this.car.turnOff();
        this.car.inspect(this.command);
        assertThat(this.data.engine, equalTo(true));
    }

    @Test(expected = EngineAlreadyOff.class)
    public void testTurnOffThrowsWhenAlreadyOff() {
        this.car = new Car(this.data);
        this.car.turnOff();
    }

    @Test
    public void testStartHeadlightsWithEngineOnStartsHeadlights() {
        this.data.engine = true;
        this.car = new Car(this.data);
        this.car.start(Components.HEADLIGHTS);
        this.car.inspect(this.command);
        assertThat(this.command.data.headlights, equalTo(true));
    }

    @Test(expected = HeadlightsAlreadyOn.class)
    public void testStartHeadlightsThrowsWhenAlreadyOn() {
        this.data.engine = true;
        this.data.headlights = true;
        this.car = new Car(this.data);
        this.car.start(Components.HEADLIGHTS);
    }

    @Test(expected = EngineOff.class)
    public void testStartHeadlightsThrowsWithEngineOff() {
        this.car = new Car(this.data);
        this.car.start(Components.HEADLIGHTS);
    }

    @Test
    public void testStartStereoWithEngineOnStartsStereo() {
        this.data.engine = true;
        this.car = new Car(this.data);
        this.car.start(Components.STEREO);
        this.car.inspect(this.command);
        assertThat(this.command.data.stereo, equalTo(true));
    }

    @Test(expected = StereoAlreadyOn.class)
    public void testStartStereoThrowsWhenAlreadyOn() {
        this.data.engine = true;
        this.data.stereo = true;
        this.car = new Car(this.data);
        this.car.start(Components.STEREO);
    }

    @Test(expected = EngineOff.class)
    public void testStartStereoThrowsWithEngineOff() {
        this.car = new Car(this.data);
        this.car.start(Components.STEREO);
    }

    @Test
    public void testStartEngineWithEngineOffStartsEngine() {
        this.car = new Car(this.data);
        this.car.start(Components.ENGINE);
        this.car.inspect(this.command);
        assertThat(this.command.data.engine, equalTo(true));
    }

    @Test(expected = EngineAlreadyStarted.class)
    public void testStartEngineThrowsWhenAlreadyOn() {
        this.data.engine = true;
        this.car = new Car(this.data);
        this.car.start(Components.ENGINE);
    }

    @Test
    public void testTurnOffHeadlightsWithEngineOnTurnsOffHeadlights() {
        this.data.engine = true;
        this.data.headlights = true;
        this.car = new Car(this.data);
        this.car.turnOff(Components.HEADLIGHTS);
        this.car.inspect(this.command);
        assertThat(this.command.data.headlights, equalTo(false));
    }

    @Test(expected = HeadlightsAlreadyOff.class)
    public void testTurnOffHeadlightsThrowsWhenAlreadyOff() {
        this.data.engine = true;
        this.car = new Car(this.data);
        this.car.turnOff(Components.HEADLIGHTS);
    }

    @Test(expected = EngineOff.class)
    public void testTurnOffHeadlightsThrowsWithEngineOff() {
        this.data.headlights = true;
        this.car = new Car(this.data);
        this.car.turnOff(Components.HEADLIGHTS);
    }

    @Test
    public void testTurnOffStereoWithEngineOnTurnsOffStereo() {
        this.data.engine = true;
        this.data.stereo = true;
        this.car = new Car(this.data);
        this.car.turnOff(Components.STEREO);
        this.car.inspect(this.command);
        assertThat(this.command.data.stereo, equalTo(false));
    }

    @Test(expected = StereoAlreadyOff.class)
    public void testTurnOffStereoThrowsWhenAlreadyOff() {
        this.data.engine = true;
        this.car = new Car(this.data);
        this.car.turnOff(Components.STEREO);
    }

    @Test
    public void testTurnOffEngineWhenOnTurnsOffEngine() {
        this.data.engine = true;
        this.car = new Car(this.data);
        this.car.turnOff(Components.ENGINE);
        this.car.inspect(this.command);
        assertThat(this.command.data.engine, equalTo(false));
    }

    @Test
    public void testTurnOffEngineTurnsOffHeadlightsToo() {
        this.data.engine = true;
        this.data.headlights = true;
        this.car = new Car(this.data);
        this.car.turnOff(Components.ENGINE);
        this.car.inspect(this.command);
        assertThat(this.command.data.headlights, equalTo(false));
    }

    @Test(expected = EngineAlreadyOff.class)
    public void testTurnOffEngineThrowsWhenAlreadyOff() {
        this.car = new Car(this.data);
        this.car.turnOff(Components.ENGINE);
    }
}
