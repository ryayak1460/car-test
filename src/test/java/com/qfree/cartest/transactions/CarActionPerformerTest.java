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

import org.junit.Before;
import org.junit.Test;

import com.qfree.cartest.helpers.HandlerFactoryImpl;
import com.qfree.cartest.helpers.HandlerType;
import com.qfree.cartest.helpers.PerformCarActionHandler;
import com.qfree.cartest.transactions.HandlerFactory;
import com.qfree.cartest.transactions.data.Action;
import com.qfree.cartest.transactions.data.Component;
import com.qfree.cartest.transactions.data.PerformCarActionRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CarActionPerformerTest {
    private PerformCarActionHandler handler;
    private CarActionPerformer transaction;

    @Before
    public void housekeeping() {
        HandlerFactory factory = new HandlerFactoryImpl();
        String type = HandlerType.PERFORM_ACTION.toString();
        this.handler = (PerformCarActionHandler)factory.make(type);
        this.transaction = new CarActionPerformer(handler);
    }

    @Test
    public void testNoActionDoesNothingToAnEngine() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.NOTHING;
        request.car.engine = true;
        transaction.process(request);
        assertThat(handler.response.car.engine, equalTo(true));
    }

    @Test
    public void testNothingDoesNothingToTheHeadlights() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.NOTHING;
        request.component = Component.HEADLIGHTS;
        request.car.headlights = true;
        transaction.process(request);
        assertThat(handler.response.car.headlights, equalTo(true));
    }

    @Test
    public void testNothingDoesNothingToTheStereo() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.NOTHING;
        request.component = Component.STEREO;
        request.car.stereo = true;
        transaction.process(request);
        assertThat(handler.response.car.stereo, equalTo(true));
    }

    @Test
    public void testTurnOnEngineWhenOffStartsEngine() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_ON;
        request.component = Component.ENGINE;
        request.car.engine = false;
        transaction.process(request);
        assertThat(handler.response.car.engine, equalTo(true));
    }

    @Test
    public void testTurnOnHeadlightsWhenEngineOnAndHeadlightsOnStartsHeadlights() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_ON;
        request.component = Component.HEADLIGHTS;
        request.car.engine = true;
        request.car.headlights = false;
        transaction.process(request);
        assertThat(handler.response.car.headlights, equalTo(true));
    }

    @Test
    public void testTurnOnStereoWhenEngineOnAndStereoOffStartsStereo() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_ON;
        request.component = Component.STEREO;
        request.car.engine = true;
        request.car.stereo = false;
        transaction.process(request);
        assertThat(handler.response.car.stereo, equalTo(true));
    }

    @Test
    public void testTurnOnEngineWhenEngineAlreadyOnReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_ON;
        request.component = Component.ENGINE;
        request.car.engine = true;
        transaction.process(request);
        assertThat(handler.response.error.message,
            equalTo("Engine already started."));
    }

    @Test
    public void testTurnOnHeadlightsWhenEngineOffReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_ON;
        request.component = Component.HEADLIGHTS;
        request.car.engine = false;
        transaction.process(request);
        assertThat(handler.response.error.message, equalTo("Engine off."));
    }

    @Test
    public void testTurnOnHeadlightsWhenAlreadyOnReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_ON;
        request.component = Component.HEADLIGHTS;
        request.car.engine = true;
        request.car.headlights = true;
        transaction.process(request);
        assertThat(handler.response.error.message,
            equalTo("Headlights already on."));
    }

    @Test
    public void testTurnOnStereoWhenEngineOffReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_ON;
        request.component = Component.STEREO;
        request.car.engine = false;
        transaction.process(request);
        assertThat(handler.response.error.message, equalTo("Engine off."));
    }

    @Test
    public void testTurnOnStereoWhenAlreadyOnReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_ON;
        request.component = Component.STEREO;
        request.car.engine = true;
        request.car.stereo = true;
        transaction.process(request);
        assertThat(handler.response.error.message,
            equalTo("Stereo already on."));
    }

    @Test
    public void testTurnOffEngineWhenEngineOnTurnsEverythingOff() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_OFF;
        request.component = Component.ENGINE;
        request.car.engine = true;
        request.car.headlights = true;
        request.car.stereo = true;
        transaction.process(request);
        assertThat(handler.response.car.engine, equalTo(false));
        assertThat(handler.response.car.headlights, equalTo(false));
        assertThat(handler.response.car.stereo, equalTo(false));
    }

    @Test
    public void testTurnOffHeadlightsWhenEngineOnAndHeadlightsOnTurnsOff() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_OFF;
        request.component = Component.HEADLIGHTS;
        request.car.engine = true;
        request.car.headlights = true;
        transaction.process(request);
        assertThat(handler.response.car.headlights, equalTo(false));
    }

    @Test
    public void testTurnOffStereoWhenEngineOnAndStereoOnTurnsOffStereo() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_OFF;
        request.component = Component.STEREO;
        request.car.engine = true;
        request.car.stereo = true;
        transaction.process(request);
        assertThat(handler.response.car.stereo, equalTo(false));
    }

    @Test
    public void testTurnOffEngineWhenAlreadyOffReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_OFF;
        request.component = Component.ENGINE;
        request.car.engine = false;
        transaction.process(request);
        assertThat(handler.response.error.message,
            equalTo("Engine already off."));
    }

    @Test
    public void testTurnOffHeadlightsWhenEngineOffReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_OFF;
        request.component = Component.HEADLIGHTS;
        request.car.engine = false;
        transaction.process(request);
        assertThat(handler.response.error.message, equalTo("Engine off."));
    }

    @Test
    public void testTurnOffHeadlightsWhenAlreadyOffReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_OFF;
        request.component = Component.HEADLIGHTS;
        request.car.engine = true;
        request.car.headlights = false;
        transaction.process(request);
        assertThat(handler.response.error.message,
            equalTo("Headlights already off."));
    }

    @Test
    public void testTurnOffStereoWhenEngineOffReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_OFF;
        request.component = Component.STEREO;
        request.car.engine = false;
        transaction.process(request);
        assertThat(handler.response.error.message, equalTo("Engine off."));
    }

    @Test
    public void testTurnOffStereoWhenAlreadyOffReturnsError() {
        PerformCarActionRequest request = new PerformCarActionRequest();
        request.action = Action.TURN_OFF;
        request.component = Component.STEREO;
        request.car.engine = true;
        request.car.stereo = false;
        transaction.process(request);
        assertThat(handler.response.error.message,
            equalTo("Stereo already off."));
    }
}
