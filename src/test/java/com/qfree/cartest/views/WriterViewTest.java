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

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;

import com.qfree.cartest.presenters.data.Text;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WriterViewTest {
    private Writer writer;
    private WriterView view;

    @Before
    public void housekeeping() {
        this.writer = mock(Writer.class);
        this.view = new WriterView(writer);
    }

    @Test
    public void testNoLinesWritesNothing() throws IOException {
        Text model = new Text();
        view.render(model);
        verify(writer, never()).write(anyString());
    }

    @Test
    public void testOneLineWritesOnce() throws IOException {
        Text model = new Text();
        model.lines.add("a line of text");
        view.render(model);
        verify(writer, times(1)).write("a line of text\n");
        verify(writer, times(1)).close();
    }
}
