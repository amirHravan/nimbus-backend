package edu.sharif.nimbus.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sharif.nimbus.model.dto.country.CountryName;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class RemoteCountryNameListDto {
    @JsonProperty("error")
    private final boolean hasError;
    @JsonProperty("msg")
    private final String message;
    @JsonProperty("data")
    private final RemoteCountryNameDto[] countryDtoList;

    public ArrayList<CountryName> getCountryNames() {
        ArrayList<CountryName> result = new ArrayList<>();
        for (RemoteCountryNameDto remoteCountryNameDto : this.countryDtoList) {
            result.add(new CountryName(remoteCountryNameDto.getName()));
        }
        return result;
    }
}
