package edu.sharif.nimbus.model.dto.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@AllArgsConstructor
@Data
public class CountryNameListDto {
    @JsonProperty("countries")
    public final CountryNameDto[] countries;
    @JsonProperty("count")
    public final int count;

    public CountryNameListDto(CountryName[] countryNames) {
        this.countries = Arrays.stream(countryNames).map(countryName -> new CountryNameDto(countryName.getName())).toArray(CountryNameDto[]::new);
        this.count = countries.length;
    }
}
