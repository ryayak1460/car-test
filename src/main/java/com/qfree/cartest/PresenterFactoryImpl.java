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

import com.qfree.cartest.transactions.HandlerFactory;
import com.qfree.cartest.transactions.Handler;

public class PresenterFactoryImpl implements HandlerFactory {
    private ViewFactory factory;

    public PresenterFactoryImpl(ViewFactory factory) {
        this.factory = factory;
    }

    public Handler make(String type) {
        View view = factory.make(type);
        switch (Presenters.valueOf(type)) {
            case LOT:
                return new LotPresenter(view);

            case ACTION:
                return new ActionPresenter(view);
        }
        return null;
    }
}
