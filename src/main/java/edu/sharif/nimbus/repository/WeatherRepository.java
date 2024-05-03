package edu.sharif.nimbus.repository;

import edu.sharif.nimbus.model.Weather;
import edu.sharif.nimbus.repository.dto.RemoteWeatherDto;
import edu.sharif.nimbus.util.URLs;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherRepository {
    private final RestTemplate restTemplate;

    public WeatherRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Weather getCountryWeatherByName(String name) {
        RemoteWeatherDto data = restTemplate.getForObject(URLs.WEATHER.url + name, RemoteWeatherDto.class);
        assert data != null;
        return data.toWeather(name);
    }

}
