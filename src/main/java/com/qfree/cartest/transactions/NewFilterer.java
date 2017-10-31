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
package com.qfree.cartest.transactions;

import java.util.Calendar;
import java.util.List;

import com.qfree.cartest.transactions.data.FilterNewRequest;
import com.qfree.cartest.transactions.data.FilterNewResponse;
import com.qfree.cartest.transactions.data.CarData;

import static java.util.stream.Collectors.toList;

public class NewFilterer {
    private Handler handler;

    public NewFilterer(Handler handler) {
        this.handler = handler;
    }

    public void process(FilterNewRequest request) {
        FilterNewResponse response = new FilterNewResponse();
        int year = getCurrentYear();
        List<CarData> newCars = request.cars.stream()
            .filter(car -> car.year >= year).collect(toList());
        response.cars.addAll(newCars);
        this.handler.handle(response);
    }

    private int getCurrentYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }
}
