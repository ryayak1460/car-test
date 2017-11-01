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
package com.qfree.cartest;

import java.util.ArrayList;
import java.util.List;

import com.qfree.cartest.presenters.PresenterFactoryImpl;
import com.qfree.cartest.presenters.Presenters;
import com.qfree.cartest.transactions.data.Action;
import com.qfree.cartest.transactions.data.BuildLotRequest;
import com.qfree.cartest.transactions.data.CarData;
import com.qfree.cartest.transactions.data.CarWithComponentsData;
import com.qfree.cartest.transactions.data.Component;
import com.qfree.cartest.transactions.data.FilterNewRequest;
import com.qfree.cartest.transactions.data.PerformCarActionRequest;
import com.qfree.cartest.transactions.CarActionPerformer;
import com.qfree.cartest.transactions.Handler;
import com.qfree.cartest.transactions.HandlerFactory;
import com.qfree.cartest.transactions.LotBuilder;
import com.qfree.cartest.transactions.NewFilterer;
import com.qfree.cartest.views.ViewFactoryImpl;

import static java.util.stream.Collectors.toList;

public class App {
    private final List<CarWithComponentsData> cars;
    private CarWithComponentsData spectra;
    private CarWithComponentsData focus;
    private CarWithComponentsData model3;
    private CarWithComponentsData modelS;
    private CarWithComponentsData studebaker;
    private ViewFactoryImpl viewFactory;
    private HandlerFactory presenterFactory;

    App() {
        cars = new ArrayList<CarWithComponentsData>();
    }

    public static void main(String args[]) {
        System.out.println("App started!");

        App carTest = new App();
        carTest.run();
    }

    public void run() {
        this.viewFactory = new ViewFactoryImpl();
        this.presenterFactory = new PresenterFactoryImpl(viewFactory);
        makeCarList();

        /**
         * Step 1:
         *
         * Create two(2) or more car implementations and add those
         * cars to the car lot.  After building out the lot, print the inventory
         * of the lot utilizing Java 8 streams to include all relevant attributes
         * of the vehicles (make/model/year).  At least 1 of the new cars must
         * be model year 2016 or later
         */
         viewFactory.setPath("question1_out.txt");
         buildCarLot();


        /**
         * Step 2:
         *
         * Create a filter predicate to only print out new cars and print those cars
         */
         viewFactory.setPath("question2_out.txt");
         filterNewCars();


        /**
         * Step 3:
         *
         * - Create Engine / Headlights / Stereo components
         * - Modify the vehicle creation to pass in instances of each component for each car
         * - Finish building out the turn-on/turn-off function to send a command to each component and
         *       print out a message indicating each component has turned on or off
         *              e.x. "Engine has started"
         * - Make sure to add error and exception handling if attempting to start a vehicle that's
         *   already started or stop one that's already stopped.
         * - Invoke the turn on and turn off method for each car in the lot, printing all
         *   status to the console
         */
         viewFactory.setPath("question3_out.txt");
         startAllCars();
         stopAllCars();
    }

    void makeCarList() {
        makeSpectra();
        makeFocus();
        makeModel3();
        makeModelS();
        makeStudebaker();
        this.cars.add(spectra);
        this.cars.add(focus);
        this.cars.add(model3);
        this.cars.add(modelS);
        this.cars.add(studebaker);
    }

    private void makeSpectra() {
        spectra = new CarWithComponentsData();
        spectra.year = 2008;
        spectra.make = "Kia";
        spectra.model = "Spectra";
    }

    private void makeFocus() {
        focus = new CarWithComponentsData();
        focus.year = 2013;
        focus.make = "Ford";
        focus.model = "Focus";
    }

    private void makeModel3() {
        model3 = new CarWithComponentsData();
        model3.year = 2018;
        model3.make = "Tesla";
        model3.model = "Model 3";
    }

    private void makeModelS() {
        modelS = new CarWithComponentsData();
        modelS.year = 2017;
        modelS.make = "Tesla";
        modelS.model = "Model S 100D";
    }

    private void makeStudebaker() {
        studebaker = new CarWithComponentsData();
        studebaker.year = 1938;
        studebaker.make = "Studebaker";
        studebaker.model = "State Commander";
    }

    void buildCarLot() {
        String type = Presenters.LOT.toString();
        LotBuilder transaction = new LotBuilder(presenterFactory.make(type));
        BuildLotRequest request = new BuildLotRequest();
        request.lot.cars.add((CarData)spectra);
        request.lot.cars.add((CarData)focus);
        request.cars.add((CarData)model3);
        request.cars.add((CarData)modelS);
        request.cars.add((CarData)studebaker);
        transaction.process(request);
    }

    void filterNewCars() {
        String type = Presenters.LOT.toString();
        NewFilterer transaction = new NewFilterer(presenterFactory.make(type));
        FilterNewRequest request = new FilterNewRequest();
        request.cars = this.cars.stream()
            .map(car -> (CarData)car).collect(toList());
        transaction.process(request);
    }

    void startAllCars() {
        cars.stream().forEach(this::start);
    }

    private void start(CarWithComponentsData car) {
        String type = Presenters.ACTION.toString();
        Handler handler = presenterFactory.make(type);
        CarActionPerformer transaction = new CarActionPerformer(handler);
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_ON;
        request.component = Component.ENGINE;
        request.car = car;
        transaction.process(request);
    }

    void stopAllCars() {
        cars.stream().forEach(this::stop);
    }

    private void stop(CarWithComponentsData car) {
        String type = Presenters.ACTION.toString();
        CarActionPerformer transaction = new CarActionPerformer(presenterFactory.make(type));
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_OFF;
        request.component = Component.ENGINE;
        request.car = car;
        transaction.process(request);
    }
}
