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

import com.qfree.cartest.helpers.TextView;
import com.qfree.cartest.helpers.ViewFactoryImpl;
import com.qfree.cartest.helpers.Views;
import com.qfree.cartest.transactions.data.Action;
import com.qfree.cartest.transactions.data.CarWithComponentsData;
import com.qfree.cartest.transactions.data.Component;
import com.qfree.cartest.transactions.data.Error;
import com.qfree.cartest.transactions.data.PerformCarActionResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ActionPresenterTest {
    private TextView view;
    private ActionPresenter presenter;

    @Before
    public void housekeeping() {
        ViewFactory factory = new ViewFactoryImpl();
        this.view = (TextView)factory.make(Views.TEXT.toString());
        this.presenter = new ActionPresenter(view);
    }

    @Test
    public void testErrorReturnsMessage() {
        PerformCarActionResponse response = new PerformCarActionResponse();
        response.error = new Error();
        response.error.message = "We have an error message.";
        presenter.handle(response);
        assertThat(view.model.lines.get(0),
            equalTo("We have an error message."));
    }

    @Test
    public void testSuccessReturnsComponentMessage() {
        String message =
            "Successfully turned on the headlights of the 2008 Kia Spectra.";
        PerformCarActionResponse response = new PerformCarActionResponse();
        addSpectraTo(response);
        response.action = Action.TURN_ON;
        response.component = Component.HEADLIGHTS;
        presenter.handle(response);
        assertThat(view.model.lines.get(0), equalTo(message));
    }

    private void addSpectraTo(PerformCarActionResponse response) {
        response.car = new CarWithComponentsData();
        response.car.year = 2008;
        response.car.make = "Kia";
        response.car.model = "Spectra";
        response.car.engine = true;
        response.car.headlights = true;
        response.car.stereo = false;
    }

    @Test
    public void testSuccessDefinitelyReturnsComponentMessage() {
        PerformCarActionResponse response = new PerformCarActionResponse();
        String message =
            "Successfully turned off the stereo of the 2013 Ford Focus.";
        addFocusTo(response);
        response.action = Action.TURN_OFF;
        response.component = Component.STEREO;
        presenter.handle(response);
        assertThat(view.model.lines.get(0), equalTo(message));
    }

    private void addFocusTo(PerformCarActionResponse response) {
        response.car = new CarWithComponentsData();
        response.car.year = 2013;
        response.car.make = "Ford";
        response.car.model = "Focus";
        response.car.engine = true;
        response.car.headlights = false;
        response.car.stereo = false;
    }
}
