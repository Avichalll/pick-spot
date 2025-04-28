package com.pickspot.pick_spot.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.pickspot.pick_spot.dto.PickRequest;
import com.pickspot.pick_spot.dto.PickResponse;
import com.pickspot.pick_spot.model.Container;
import com.pickspot.pick_spot.model.YardSlot;

public class PickSpotServiceTest {

    @Test
    void testChooseBestSlot() {
        PickSpotService service = new PickSpotService();

        Container container = new Container("C1", "small", false, 1, 1);

        YardSlot slot1 = new YardSlot(1, 2, "small", false, false);
        YardSlot slot2 = new YardSlot(2, 2, "big", true, false);

        PickRequest request = new PickRequest(container, Arrays.asList(slot1, slot2));

        PickResponse response = service.chooseBestSlot(request);

        assertNotNull(response);
        assertEquals("C1", response.getContainerId());
        assertEquals(1, response.getTargetX());
        assertEquals(2, response.getTargetY());
    }

    @Test
    void testNoSuitableSlot() {
        PickSpotService service = new PickSpotService();

        Container container = new Container("C1", "big", true, 1, 1);

        YardSlot slot1 = new YardSlot(1, 2, "small", false, false);
        YardSlot slot2 = new YardSlot(2, 2, "small", false, false);

        PickRequest request = new PickRequest(container, Arrays.asList(slot1, slot2));

        PickResponse response = service.chooseBestSlot(request);

        assertNotNull(response);
        assertEquals("no suitable slot", response.getError());
    }

}
