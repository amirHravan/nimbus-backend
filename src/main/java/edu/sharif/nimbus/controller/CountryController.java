package edu.sharif.nimbus.controller;

import edu.sharif.nimbus.model.dto.country.CountryDto;
import edu.sharif.nimbus.model.dto.country.CountryNameListDto;
import edu.sharif.nimbus.model.dto.country.WeatherDto;
import edu.sharif.nimbus.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("")
    public CountryNameListDto getCountryList(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ) {
        return new CountryNameListDto(countryService.getAllCountriesNames(authorization, page, limit));
    }

    @GetMapping("/{name}")
    public CountryDto getCountry(
            @PathVariable String name,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        return new CountryDto(countryService.getCountryByName(name, authorization));
    }

    @GetMapping("/{name}/weather")
    public WeatherDto getCountryWeather(
            @PathVariable String name,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        return new WeatherDto(countryService.getCountryCapitalWeatherByCountryName(name, authorization));
    }

}
