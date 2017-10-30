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
package com.qfree.cartest.models;

import com.qfree.cartest.actions.CarActions;

public interface ICar extends CarActions {
    /**
     * @return the make of the car
     */
    String getCarMake();

    /**
     * @return the model of the car
     */
    String getCarModel();

    /**
     * @return the year the car was manufactured
     */
    String getCarYear();
}
