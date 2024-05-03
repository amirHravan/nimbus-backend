package edu.sharif.nimbus.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sharif.nimbus.model.Currency;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class RemoteCurrencyDto {
    @JsonProperty("code")
    private final String code;
    @JsonProperty("name")
    private final String name;

    public Currency toCurrency() {
        return new Currency(code, name);
    }
}
