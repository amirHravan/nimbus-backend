package edu.sharif.nimbus.service;

import edu.sharif.nimbus.model.Country;
import edu.sharif.nimbus.model.Weather;
import edu.sharif.nimbus.model.dto.country.CountryName;
import edu.sharif.nimbus.repository.dto.RemoteCountryDto;
import edu.sharif.nimbus.repository.dto.RemoteCountryNameListDto;
import edu.sharif.nimbus.repository.dto.RemoteWeatherDto;
import edu.sharif.nimbus.util.URLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {
    final UserService userService;
    private final RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public CountryService(UserService userService, RestTemplate restTemplate, String ninjaApiToken) {
        this.userService = userService;
        this.restTemplate = restTemplate;
        headers.set("x-api-key", ninjaApiToken);
    }

    @Cacheable("countries")
    public CountryName[] getAllCountriesNames(String authorization, int page, int limit) {
        userService.authorizeUser(authorization);
        RemoteCountryNameListDto data = restTemplate.getForObject(URLs.COUNTRIES.url, RemoteCountryNameListDto.class);
        assert data != null;
        return data.getCountryNames(page, limit).toArray(CountryName[]::new);
    }

    @Cacheable("country")
    public Country getCountryByName(String name, String authorization) {
        userService.authorizeUser(authorization);
        HttpEntity<RemoteCountryDto> requestEntity = new HttpEntity<>(headers);
        RemoteCountryDto[] data = restTemplate.exchange(URLs.COUNTRY.url + name, HttpMethod.GET, requestEntity, RemoteCountryDto[].class).getBody();
        assert data != null;
        return data[0].toCountry(name);
    }

    @Cacheable("weather")
    public Weather getCountryCapitalWeatherByCountryName(String countryName, String authorization) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        Country country = getCountryByName(countryName, authorization);
        RemoteWeatherDto data = restTemplate.exchange(URLs.WEATHER.url + country.getCapital(),
                HttpMethod.GET,
                requestEntity,
                RemoteWeatherDto.class
        ).getBody();
        assert data != null;
        return data.toWeather(countryName, country.getCapital());
    }

    @CacheEvict(cacheNames = {"countries", "country", "weather"}, allEntries = true)
    @Scheduled(fixedRateString = "${spring.cache.time-to-live}")
    public void cacheEvict() {}
}
