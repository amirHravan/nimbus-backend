package edu.sharif.nimbus.service;

import edu.sharif.nimbus.model.Weather;
import edu.sharif.nimbus.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final CountryRepository weatherRepository;
    private final UserService userService;

    public Weather getCountryWeatherByName(String name, String authorization) {
        userService.authorizeUser(authorization);
        return weatherRepository.getCountryCapitalWeatherByCountryName(name);
    }
}
