package edu.sharif.nimbus.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sharif.nimbus.model.Weather;
import lombok.Data;

@Data
public class WeatherDto {
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("capital")
    private String capital;
    @JsonProperty("windSpeed")
    private float windSpeed;
    @JsonProperty("windDegrees")
    private int windDegrees;
    @JsonProperty("temp")
    private int temp;
    @JsonProperty("humidity")
    private int humidity;

    public WeatherDto(Weather weather) {
        countryName = weather.getCountryName();
        capital = weather.getCapital();
        windSpeed = weather.getWindSpeed();
        windDegrees = weather.getWindDegrees();
        temp = weather.getTemperature();
        humidity = weather.getHumidity();
    }
}
