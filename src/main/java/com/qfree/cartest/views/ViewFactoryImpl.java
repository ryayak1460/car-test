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

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.qfree.cartest.presenters.View;
import com.qfree.cartest.presenters.ViewFactory;

public class ViewFactoryImpl implements ViewFactory {
    private String path = "output.txt";

    public View make(String type) {
        Writer writer = tryToMakeFileWriter();
        return new WriterView(writer);
    }

    private Writer tryToMakeFileWriter() {
        try {
            return makeFileWriter();
        } catch (IOException exception) {
            return null;
        }
    }

    private Writer makeFileWriter() throws IOException {
        return new FileWriter(this.path);
    }

    public void setPath(String path) {
        this.path = path;
    }
}
