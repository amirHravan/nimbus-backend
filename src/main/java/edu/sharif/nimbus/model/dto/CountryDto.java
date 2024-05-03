package edu.sharif.nimbus.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sharif.nimbus.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class CountryDto {
    @JsonProperty("name")
    private final String name;
    @JsonProperty("capital")
    private String capital;
    @JsonProperty("iso2")
    private String iso2;
    @JsonProperty("population")
    private double population;
    @JsonProperty("pop_growth")
    private float populationGrowth;
    @JsonProperty("currency")
    private CurrencyDto currency;

    public CountryDto(Country country) {
        this.name = country.getName();
        this.capital = country.getCapital();
        this.iso2 = country.getIso2();
        this.population = country.getPopulation();
        this.populationGrowth = country.getPopGrowth();
        this.currency = new CurrencyDto(country.getCurrency());
    }
}
