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
package com.qfree.cartest.presenters;

import java.util.List;

import com.qfree.cartest.presenters.data.Text;
import com.qfree.cartest.transactions.Handler;
import com.qfree.cartest.transactions.data.BuildLotResponse;
import com.qfree.cartest.transactions.data.CarData;
import com.qfree.cartest.transactions.data.FilterNewResponse;
import com.qfree.cartest.transactions.data.Response;

import static java.util.stream.Collectors.toList;

public class LotPresenter implements Handler {
    private View view;

    public LotPresenter(View view) {
        this.view = view;
    }

    public void handle(Response response) {
        List<CarData> cars = getCars(response);
        Text model = cars.isEmpty() ?
            makeEmptyModel() :
            makeFullLot(cars);
        this.view.render(model);
    }

    private List<CarData> getCars(Response response) {
        try {
            BuildLotResponse lot = (BuildLotResponse)response;
            return lot.cars;
        } catch (Exception exception) {
            FilterNewResponse filter = (FilterNewResponse)response;
            return filter.cars;
        }
    }

    private Text makeEmptyModel() {
        Text model = new Text();
        model.lines.add("Empty lot.");
        return model;
    }

    private Text makeFullLot(List<CarData> cars) {
        Text model = new Text();
        model.lines.add("The lot has the following cars:");
        List<String> entries = cars.stream().map(car -> "- "
            + String.valueOf(car.year)
            + " " + car.make + " " + car.model).collect(toList());
        model.lines.addAll(entries);
        return model;
    }
}
