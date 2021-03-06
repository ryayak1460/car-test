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
package com.qfree.cartest.helpers;

import com.qfree.cartest.entities.data.LotData;
import com.qfree.cartest.entities.data.Command;
import com.qfree.cartest.entities.data.Data;

public class LotCommand implements Command {
    public LotData data;

    public void execute(Data data) {
        LotData lot = (LotData)data;
        this.data = lot;
    }
}
