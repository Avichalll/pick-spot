package com.pickspot.pick_spot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pickspot.pick_spot.dto.PickRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PickSpotController {

    private final PickSpotService pickSpotService;

    @PostMapping("/pickSpot")
    public ResponseEntity<?> chooseBestSlot(@RequestBody PickRequest pickRequest) {
        return ResponseEntity.ok(pickSpotService.chooseBestSlot(pickRequest));
    }

}
