package edu.sharif.nimbus.repository;

import edu.sharif.nimbus.model.Country;
import edu.sharif.nimbus.model.Weather;
import edu.sharif.nimbus.model.dto.country.CountryName;
import edu.sharif.nimbus.repository.dto.RemoteCountryDto;
import edu.sharif.nimbus.repository.dto.RemoteCountryNameListDto;
import edu.sharif.nimbus.repository.dto.RemoteWeatherDto;
import edu.sharif.nimbus.util.URLs;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CountryRepository {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();

    public CountryRepository(RestTemplate restTemplate, String ninjaApiToken) {
        this.restTemplate = restTemplate;
        headers.set("x-api-key", ninjaApiToken);
    }

    public ArrayList<CountryName> getAllCountriesNames() {
        RemoteCountryNameListDto data = restTemplate.getForObject(URLs.COUNTRIES.url, RemoteCountryNameListDto.class);
        assert data != null;
        return data.getCountryNames();
    }

    public Country getCountryByName(String name) {
        HttpEntity<RemoteCountryDto> requestEntity = new HttpEntity<>(headers);
        RemoteCountryDto[] data = restTemplate.exchange(URLs.COUNTRY.url + name, HttpMethod.GET, requestEntity, RemoteCountryDto[].class).getBody();
        assert data != null;
        return data[0].toCountry(name);
    }

    public Weather getCountryCapitalWeatherByCountryName(String countryName) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        Country country = getCountryByName(countryName);
        RemoteWeatherDto data = restTemplate.exchange(URLs.WEATHER.url + country.getCapital(),
                HttpMethod.GET,
                requestEntity,
                RemoteWeatherDto.class
        ).getBody();
        assert data != null;
        return data.toWeather(countryName, country.getCapital());
    }
}
