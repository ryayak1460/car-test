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
import com.qfree.cartest.entities.errors.EngineAlreadyOff;
import com.qfree.cartest.entities.errors.EngineAlreadyStarted;
import com.qfree.cartest.entities.errors.EngineOff;
import com.qfree.cartest.entities.errors.HeadlightsAlreadyOff;
import com.qfree.cartest.entities.errors.HeadlightsAlreadyOn;
import com.qfree.cartest.entities.errors.StereoAlreadyOff;
import com.qfree.cartest.entities.errors.StereoAlreadyOn;

public class Car implements Inspectable {
    private CarData data;

    public Car(CarData data) {
        this.data = new CarData();
        this.data.make = data.make;
        this.data.model = data.model;
        this.data.year = data.year;
        this.data.engine = data.engine;
        this.data.headlights = data.headlights;
        this.data.stereo = data.stereo;
    }

    public void start() {
        if (theEngineIsOn())
            throw new EngineAlreadyStarted();

        this.data.engine = true;
    }

    private boolean theEngineIsOn() {
        return this.data.engine;
    }

    public void start(Components type) {
        if (type == Components.ENGINE)
            this.start();

        if (!theEngineIsOn())
            throw new EngineOff();

        if (type == Components.HEADLIGHTS)
            this.startHeadlights();

        if (type == Components.STEREO)
            this.startStereo();
    }

    private void startHeadlights() {
        if (theHeadlightsAreOn())
            throw new HeadlightsAlreadyOn();
        this.data.headlights = true;
    }

    private boolean theHeadlightsAreOn() {
        return this.data.headlights;
    }

    private void startStereo() {
        if (theStereoIsOn())
            throw new StereoAlreadyOn();

        this.data.stereo = true;
    }

    private boolean theStereoIsOn() {
        return this.data.stereo;
    }

    public void turnOff() {
        if (!theEngineIsOn())
            throw new EngineAlreadyOff();

        this.data.engine = false;
        this.data.headlights = false;
        this.data.stereo = false;
    }

    public void turnOff(Components type) {
        if (type == Components.ENGINE)
            this.turnOff();
        else if (!theEngineIsOn())
            throw new EngineOff();

        if (type == Components.HEADLIGHTS)
            this.turnOffHeadlights();

        if (type == Components.STEREO)
            this.turnOffStereo();
    }

    private void turnOffHeadlights() {
        if (!theHeadlightsAreOn())
            throw new HeadlightsAlreadyOff();

        this.data.headlights = false;
    }

    private void turnOffStereo() {
        if (!theStereoIsOn())
            throw new StereoAlreadyOff();

        this.data.stereo = false;
    }

    public void inspect(Command command) {
        command.execute(this.data);
    }
}
