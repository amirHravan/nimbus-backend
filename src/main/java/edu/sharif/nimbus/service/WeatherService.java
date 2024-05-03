package edu.sharif.nimbus.service;

import edu.sharif.nimbus.model.Weather;
import edu.sharif.nimbus.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public Weather getCountryWeatherByName(String name) {
        return weatherRepository.getCountryWeatherByName(name);
    }
}
