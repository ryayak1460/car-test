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

public class Car {
    private CarData data;

    public Car(CarData data) {
        this.data = new CarData();
        this.data.make = data.make;
        this.data.model = data.model;
        this.data.year = data.year;
        this.data.engine = data.engine;
    }

    public void start() {
        if (this.data.engine) {
            throw new EngineAlreadyStarted();
        }
        this.data.engine = true;
    }

    public void turnOff() {
        if (!this.data.engine) {
            throw new EngineAlreadyOff();
        }
        this.data.engine = false;
    }

    public void inspect(Command command) {
        command.execute(this.data);
    }
}
