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

import java.io.IOException;
import java.io.PrintWriter;

import com.qfree.cartest.presenters.View;
import com.qfree.cartest.presenters.data.Text;
import com.qfree.cartest.presenters.data.ViewModel;

public class WriterView implements View {
    private PrintWriter writer;

    public WriterView(PrintWriter writer) {
        this.writer = writer;
    }

    public void render(ViewModel model) {
        try {
            writeLines((Text)model);
        } catch (IOException exception) {
        }
    }

    private void writeLines(Text text) throws IOException {
        for (String line : text.lines)
            writer.println(line);
        writer.flush();
    }
}
