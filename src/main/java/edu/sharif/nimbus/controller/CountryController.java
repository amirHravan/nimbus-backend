package edu.sharif.nimbus.controller;

import edu.sharif.nimbus.model.dto.country.CountryDto;
import edu.sharif.nimbus.model.dto.country.CountryNameListDto;
import edu.sharif.nimbus.model.dto.country.WeatherDto;
import edu.sharif.nimbus.service.CountryService;
import edu.sharif.nimbus.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final WeatherService weatherService;

    @GetMapping("")
    public CountryNameListDto getCountryList() {
        return new CountryNameListDto(countryService.getAllCountriesNames());
    }

    @GetMapping("/{name}")
    public CountryDto getCountry(@PathVariable String name) {
        return new CountryDto(countryService.getCountryByName(name));
    }

    @GetMapping("/{name}/weather")
    public WeatherDto getCountryWeather(@PathVariable String name) {
        return new WeatherDto(weatherService.getCountryWeatherByName(name));
    }

}
