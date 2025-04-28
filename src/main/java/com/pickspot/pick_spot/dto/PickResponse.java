package com.pickspot.pick_spot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)

public class PickResponse {

    private String containerId;
    private Integer targetX;
    private Integer targetY;
    private String error;

    public PickResponse(String error) {
        this.error = error;
    }

    public PickResponse(String containerId, Integer targetX, Integer targetY) {
        this.containerId = containerId;
        this.targetX = targetX;
        this.targetY = targetY;
    }

}
