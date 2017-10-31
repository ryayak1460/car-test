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

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import com.qfree.cartest.helpers.HandlerFactoryImpl;
import com.qfree.cartest.helpers.HandlerType;
import com.qfree.cartest.helpers.FilterNewHandler;
import com.qfree.cartest.transactions.HandlerFactory;
import com.qfree.cartest.transactions.data.FilterNewRequest;
import com.qfree.cartest.transactions.data.CarData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.empty;

public class NewFiltererTest {
    private FilterNewHandler handler;
    private NewFilterer transaction;

    @Before
    public void housekeeping() {
        HandlerFactory factory = new HandlerFactoryImpl();
        String type = HandlerType.FILTER_NEW.toString();
        this.handler = (FilterNewHandler)factory.make(type);
        this.transaction = new NewFilterer(this.handler);
    }

    @Test
    public void testNothingWithEmptyLot() {
        FilterNewRequest request = new FilterNewRequest();
        transaction.process(request);
        assertThat(handler.response.cars, empty());
    }

    @Test
    public void testNothingWithOneOldCar() {
        FilterNewRequest request = new FilterNewRequest();
        addOldCarTo(request.cars);
        transaction.process(request);
        assertThat(handler.response.cars, empty());
    }

    private void addOldCarTo(List<CarData> cars) {
        CarData car = new CarData();
        car.year = 1938;
        car.make = "Studebaker";
        car.model = "State Commander";
        cars.add(car);
    }

    @Test
    public void testOneElementWithNewCar() {
        FilterNewRequest request = new FilterNewRequest();
        addNewCarTo(request.cars);
        transaction.process(request);
        assertThatCarIsTheNewCar(handler.response.cars.get(0));
    }

    private void addNewCarTo(List<CarData> cars) {
        CarData car = new CarData();
        car.year = 2018;
        car.make = "Tesla";
        car.model = "Model 3";
        cars.add(car);
    }

    private void assertThatCarIsTheNewCar(CarData car) {
        assertThat(car.year, equalTo(2018));
        assertThat(car.make, equalTo("Tesla"));
        assertThat(car.model, equalTo("Model 3"));
    }

    @Test
    public void testOneElementWithRecentCar() {
        FilterNewRequest request = new FilterNewRequest();
        addRecentCarTo(request.cars);
        transaction.process(request);
        assertThatCarIsTheRecentCar(handler.response.cars.get(0));
    }

    private void addRecentCarTo(List<CarData> cars) {
        CarData car = new CarData();
        car.year = 2017;
        car.make = "Tesla";
        car.model = "Model S 100D";
        cars.add(car);
    }

    private void assertThatCarIsTheRecentCar(CarData car) {
        assertThat(car.year, equalTo(2017));
        assertThat(car.make, equalTo("Tesla"));
        assertThat(car.model, equalTo("Model S 100D"));
    }

    @Test
    public void testNewAndRecentGetReturned() {
        FilterNewRequest request = new FilterNewRequest();
        addRecentCarTo(request.cars);
        addNewCarTo(request.cars);
        transaction.process(request);
        assertThatCarIsTheRecentCar(handler.response.cars.get(0));
        assertThatCarIsTheNewCar(handler.response.cars.get(1));
    }
}
