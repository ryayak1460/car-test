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
