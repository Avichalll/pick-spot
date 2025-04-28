package com.pickspot.pick_spot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class YardSlot {

    private int x;
    private int y;
    private String sizeCap; // "small" or "big"
    private boolean hasColdUnit;
    private boolean occupied;

}
