package edu.sharif.nimbus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Country {
    private final String name;
    private String capital;
    private String iso2;
    private double population;
    private float popGrowth;
    private Currency currency;

}
