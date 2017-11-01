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

import org.junit.Before;
import org.junit.Test;
import com.qfree.cartest.transactions.data.CarData;
import com.qfree.cartest.transactions.data.BuildLotResponse;
import com.qfree.cartest.transactions.data.FilterNewResponse;
import com.qfree.cartest.helpers.TextView;
import com.qfree.cartest.helpers.ViewFactoryImpl;
import com.qfree.cartest.helpers.Views;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LotPresenterTest {
    private TextView view;
    private LotPresenter presenter;

    @Before
    public void housekeeping() {
        ViewFactory factory = new ViewFactoryImpl();
        this.view = (TextView)factory.make(Views.TEXT.toString());
        this.presenter = new LotPresenter(view);
    }

    @Test
    public void testEmptyLotReturnsEmptyMessage() {
        BuildLotResponse response = new BuildLotResponse();
        presenter.handle(response);
        assertThat(view.model.lines.get(0), equalTo("Empty lot."));
    }

    @Test
    public void testEachCarMakesAnActualHeader() {
        BuildLotResponse response = new BuildLotResponse();
        addSpectraTo(response.cars);
        presenter.handle(response);
        assertThat(view.model.lines.get(0),
            equalTo("The lot has the following cars:"));
    }

    private void addSpectraTo(List<CarData> cars) {
        CarData car = new CarData();
        car.year = 2008;
        car.make = "Kia";
        car.model = "Spectra";
        cars.add(car);
    }

    @Test
    public void testEachCarMakesACarLine() {
        BuildLotResponse response = new BuildLotResponse();
        addSpectraTo(response.cars);
        presenter.handle(response);
        assertThat(view.model.lines.get(1), equalTo("- 2008 Kia Spectra"));
    }

    @Test
    public void testLotPresentersHandleFilterNewResponsesToo() {
        FilterNewResponse response = new FilterNewResponse();
        addSpectraTo(response.cars);
        presenter.handle(response);
        assertThat(view.model.lines.get(1), equalTo("- 2008 Kia Spectra"));
    }
}
