package edu.sharif.nimbus.service;

import edu.sharif.nimbus.model.Weather;
import edu.sharif.nimbus.repository.CountryRepository;
import edu.sharif.nimbus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final CountryRepository weatherRepository;
    private final UserRepository userRepository;

    public Weather getCountryWeatherByName(String name, String authorization) {
        userRepository.authorizeUser(authorization);
        return weatherRepository.getCountryCapitalWeatherByCountryName(name);
    }
}
