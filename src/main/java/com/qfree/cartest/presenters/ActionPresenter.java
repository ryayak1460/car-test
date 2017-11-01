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

import com.qfree.cartest.presenters.data.Text;
import com.qfree.cartest.transactions.Handler;
import com.qfree.cartest.transactions.data.Action;
import com.qfree.cartest.transactions.data.CarWithComponentsData;
import com.qfree.cartest.transactions.data.Component;
import com.qfree.cartest.transactions.data.PerformCarActionResponse;
import com.qfree.cartest.transactions.data.Response;

public class ActionPresenter implements Handler {
    private View view;

    public ActionPresenter(View view) {
        this.view = view;
    }

    public void handle(Response response) {
        PerformCarActionResponse data = (PerformCarActionResponse)response;
        Text model = new Text();
        String message = data.error == null ?
            makeSuccessMessage(data) :
            makeErrorMessage(data);
        model.lines.add(message);
        this.view.render(model);
    }

    private String makeSuccessMessage(PerformCarActionResponse response) {
        return "Managed to successfully " + toText(response);
    }

    private String toText(PerformCarActionResponse response) {
        return toText(response.action)
            + " the " + toText(response.component)
            + " of the " + toText(response.car) + ".";
    }

    private String toText(Action action) {
        return action == Action.NOTHING ?
            "do nothing to" :
            action.toString().toLowerCase().replaceAll("_", " ");
    }

    private String toText(Component component) {
        return component.toString().toLowerCase();
    }

    private String toText(CarWithComponentsData car) {
        return String.valueOf(car.year) + " " + car.make + " " + car.model;
    }

    private String makeErrorMessage(PerformCarActionResponse response) {
        return "Did not successfully " + toText(response)
            + "  Had the following error: " + response.error.message;
    }
}
