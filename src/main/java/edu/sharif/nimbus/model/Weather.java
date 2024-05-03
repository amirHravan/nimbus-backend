package edu.sharif.nimbus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private String countryName;
    private String capital;
    private float windSpeed;
    private int windDegrees;
    private int temperature;
    private int humidity;
}
