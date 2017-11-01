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
package com.qfree.cartest.views;

import org.junit.Test;

import com.qfree.cartest.presenters.ViewFactory;
import com.qfree.cartest.presenters.Presenters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class ViewFactoryImplTest {
    @Test
    public void testLotMakesFile() {
        ViewFactory factory = new ViewFactoryImpl();
        assertThat(factory.make(Presenters.LOT.toString()),
            instanceOf(WriterView.class));
    }

    @Test
    public void testActionMakesFile() {
        ViewFactory factory = new ViewFactoryImpl();
        assertThat(factory.make(Presenters.ACTION.toString()),
            instanceOf(WriterView.class));
    }
}
