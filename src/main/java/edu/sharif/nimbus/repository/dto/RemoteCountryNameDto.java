package edu.sharif.nimbus.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RemoteCountryNameDto {
    @JsonProperty("iso3")
    private final String iso3;
    @JsonProperty("iso2")
    private final String iso2;
    @JsonProperty("country")
    private final String name;
}
