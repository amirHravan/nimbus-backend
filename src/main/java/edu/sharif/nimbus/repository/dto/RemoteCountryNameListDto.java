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

    public ArrayList<CountryName> getCountryNames(int page, int limit) {
        ArrayList<CountryName> result = new ArrayList<>();
        RemoteCountryNameDto[] dtoList = this.countryDtoList;
        int maxIndex = Math.min((page + 1) * limit, dtoList.length);
        int minIndex = Math.min(page * limit, dtoList.length);
        for (int i = minIndex; i < maxIndex; i++) {
            RemoteCountryNameDto remoteCountryNameDto = dtoList[i];
            result.add(new CountryName(remoteCountryNameDto.getName()));
        }
        return result;
    }
}
