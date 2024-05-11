package edu.sharif.nimbus.model.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NameAndExpireDateDto {
    @JsonProperty("name")
    private final String name;
    @JsonProperty("expire_date")
    private final String expirationDate;

}
