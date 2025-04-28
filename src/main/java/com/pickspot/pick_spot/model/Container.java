package com.pickspot.pick_spot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Container {

    private String id;
    private String size; // "small" or "big"
    private boolean needsCold;
    private Integer x;
    private Integer y;
}
