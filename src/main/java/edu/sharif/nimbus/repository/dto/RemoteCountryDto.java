package edu.sharif.nimbus.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sharif.nimbus.model.Country;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RemoteCountryDto {
    @JsonProperty("capital")
    private String capital;
    @JsonProperty("iso2")
    private String iso2;
    @JsonProperty("population")
    private double population;
    @JsonProperty("pop_growth")
    private float popGrowth;
    @JsonProperty("currency")
    private RemoteCurrencyDto currency;

    public Country toCountry(String name) {
        return new Country(
                name,
                this.capital,
                this.iso2,
                this.population,
                this.popGrowth,
                this.currency.toCurrency()
        );
    }
}
