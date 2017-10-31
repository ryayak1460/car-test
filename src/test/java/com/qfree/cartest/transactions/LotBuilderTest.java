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

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;

import com.qfree.cartest.helpers.BuildLotHandler;
import com.qfree.cartest.helpers.HandlerFactoryImpl;
import com.qfree.cartest.helpers.HandlerType;
import com.qfree.cartest.transactions.HandlerFactory;
import com.qfree.cartest.transactions.data.BuildLotRequest;
import com.qfree.cartest.transactions.data.CarData;
import com.qfree.cartest.transactions.data.LotData;

public class LotBuilderTest {
    private BuildLotHandler handler;
    private LotBuilder transaction;

    @Before
    public void housekeeping() {
        HandlerFactory factory = new HandlerFactoryImpl();
        String type = HandlerType.BUILD_LOT.toString();
        this.handler = (BuildLotHandler)factory.make(type);
        this.transaction = new LotBuilder(this.handler);
    }

    @Test
    public void testEmptyLotHasAllCarsInIt() {
        BuildLotRequest request = new BuildLotRequest();
        addSpectraTo(request.cars);
        addFocusTo(request.cars);
        this.transaction.process(request);
        assertThatCarIsSpectra(this.handler.response.cars.get(0));
        assertThatCarIsFocus(this.handler.response.cars.get(1));
    }

    private void addSpectraTo(List<CarData> cars) {
        CarData spectra = new CarData();
        spectra.year = 2008;
        spectra.make = "Kia";
        spectra.model = "Spectra";
        cars.add(spectra);
    }

    private void addFocusTo(List<CarData> cars) {
        CarData focus = new CarData();
        focus.year = 2013;
        focus.make = "Ford";
        focus.model = "Focus";
        cars.add(focus);
    }

    private void assertThatCarIsSpectra(CarData car) {
        assertThat(car.year, equalTo(2008));
        assertThat(car.make, equalTo("Kia"));
        assertThat(car.model, equalTo("Spectra"));
    }

    private void assertThatCarIsFocus(CarData car) {
        assertThat(car.year, equalTo(2013));
        assertThat(car.make, equalTo("Ford"));
        assertThat(car.model, equalTo("Focus"));
    }

    @Test
    public void testLotIncludesNewCars() {
        BuildLotRequest request = new BuildLotRequest();
        addSpectraTo(request.lot.cars);
        addFocusTo(request.cars);
        this.transaction.process(request);
        assertThatCarIsSpectra(this.handler.response.cars.get(0));
        assertThatCarIsFocus(this.handler.response.cars.get(1));
    }

    @Test
    public void testNoCarsWithLotReturnsLot() {
        BuildLotRequest request = new BuildLotRequest();
        addFocusTo(request.lot.cars);
        addSpectraTo(request.lot.cars);
        this.transaction.process(request);
        assertThatCarIsFocus(this.handler.response.cars.get(0));
        assertThatCarIsSpectra(this.handler.response.cars.get(1));
    }
}
