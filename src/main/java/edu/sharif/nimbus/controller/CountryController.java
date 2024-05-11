package edu.sharif.nimbus.controller;

import edu.sharif.nimbus.model.dto.country.CountryDto;
import edu.sharif.nimbus.model.dto.country.CountryNameListDto;
import edu.sharif.nimbus.model.dto.country.WeatherDto;
import edu.sharif.nimbus.service.CountryService;
import edu.sharif.nimbus.service.UserService;
import edu.sharif.nimbus.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final WeatherService weatherService;

    @GetMapping("")
    public CountryNameListDto getCountryList(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        return new CountryNameListDto(countryService.getAllCountriesNames(authorization));
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
        return new WeatherDto(weatherService.getCountryWeatherByName(name, authorization));
    }

}
