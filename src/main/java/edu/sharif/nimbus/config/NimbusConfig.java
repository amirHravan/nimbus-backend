package edu.sharif.nimbus.config;

import edu.sharif.nimbus.repository.CountryRepository;
import edu.sharif.nimbus.repository.WeatherRepository;
import edu.sharif.nimbus.service.CountryService;
import edu.sharif.nimbus.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NimbusConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CountryRepository countryRepository(RestTemplate restTemplate) {
        return new CountryRepository(restTemplate);
    }

    @Bean
    CountryService countryService(CountryRepository countryRepository) {
        return new CountryService(countryRepository);
    }

    @Bean
    WeatherRepository weatherRepository(RestTemplate restTemplate) {
        return new WeatherRepository(restTemplate);
    }

    @Bean
    WeatherService weatherService(WeatherRepository weatherRepository) {
        return new WeatherService(weatherRepository);
    }
}
