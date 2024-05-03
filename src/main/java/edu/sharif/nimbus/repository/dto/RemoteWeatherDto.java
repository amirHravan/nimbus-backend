package edu.sharif.nimbus.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sharif.nimbus.model.Weather;
import lombok.Data;

@Data
public class RemoteWeatherDto {
    @JsonProperty("wind_speed")
    private float windSpeed;
    @JsonProperty("wind_degrees")
    private int windDegrees;
    @JsonProperty("temp")
    private int temperature;
    @JsonProperty("humidity")
    private int humidity;

    public Weather toWeather(String countryName) {
        return new Weather(
                countryName,
                null,
                this.windSpeed,
                this.windDegrees,
                this.temperature,
                this.humidity
        );
    }
}
