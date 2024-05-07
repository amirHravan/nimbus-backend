package edu.sharif.nimbus.config;

import edu.sharif.nimbus.repository.CountryRepository;
import edu.sharif.nimbus.repository.UserRepository;
import edu.sharif.nimbus.service.CountryService;
import edu.sharif.nimbus.service.UserService;
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
    WeatherService weatherService(CountryRepository countryRepository) {
        return new WeatherService(countryRepository);
    }

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    UserRepository userRepository() {
        return new UserRepository();
    }

}
