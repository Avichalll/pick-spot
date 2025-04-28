package com.pickspot.pick_spot.dto;

import java.util.List;

import com.pickspot.pick_spot.model.Container;
import com.pickspot.pick_spot.model.YardSlot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PickRequest {

    private Container container;
    private List<YardSlot> yardMap;

}
