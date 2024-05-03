package edu.sharif.nimbus.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sharif.nimbus.model.Currency;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CurrencyDto {
    @JsonProperty("code")
    private final String code;
    @JsonProperty("name")
    private final String name;

    public CurrencyDto(Currency currency) {
        this.name = currency.getName();
        this.code = currency.getCode();
    }
}
