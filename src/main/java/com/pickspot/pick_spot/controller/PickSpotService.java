package com.pickspot.pick_spot.controller;

import org.springframework.stereotype.Service;

import com.pickspot.pick_spot.dto.PickRequest;
import com.pickspot.pick_spot.dto.PickResponse;
import com.pickspot.pick_spot.model.Container;
import com.pickspot.pick_spot.model.YardSlot;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PickSpotService {

    private static final int INVALID_PENALTY = 10000;

    public PickResponse chooseBestSlot(PickRequest pickRequest) {

        if (pickRequest == null || pickRequest.getYardMap() == null || pickRequest.getYardMap().isEmpty()) {
            return new PickResponse("no suitable slot");
        }

        int bestScore = Integer.MAX_VALUE;
        YardSlot bestSlot = null;

        Container container = pickRequest.getContainer();
        final int containerX = container.getX();
        final int containerY = container.getY();
        final String containerSize = container.getSize();
        final boolean needsCold = container.isNeedsCold();
        final String containerId = container.getId();

        for (YardSlot slot : pickRequest.getYardMap()) {
            if (slot.isOccupied()) {
                continue;
            }

            int score = calculateScore(containerX, containerY, containerSize, needsCold, slot);

            if (score < bestScore) {
                bestScore = score;
                bestSlot = slot;
            }
        }

        if (bestScore >= INVALID_PENALTY) {
            return new PickResponse("no suitable slot");
        }

        if (bestSlot == null) {
            return new PickResponse("no suitable slot");
        }
        return new PickResponse(containerId, bestSlot.getX(), bestSlot.getY());
    }

    private int calculateScore(int containerX, int containerY, String containerSize,
            boolean needsCold, YardSlot slot) {
        // Calculate Manhattan distance
        int distance = Math.abs(containerX - slot.getX()) + Math.abs(containerY - slot.getY());

        // Check size compatibility
        boolean containerFits = "big".equals(slot.getSizeCap()) ||
                ("small".equals(slot.getSizeCap()) && "small".equals(containerSize));
        if (!containerFits) {
            return INVALID_PENALTY + distance;
        }

        // Check cold storage requirement
        if (needsCold && !slot.isHasColdUnit()) {
            return INVALID_PENALTY + distance;
        }

        return distance;
    }
}