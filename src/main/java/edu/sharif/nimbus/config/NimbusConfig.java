package edu.sharif.nimbus.config;

import edu.sharif.nimbus.repository.CountryRepository;
import edu.sharif.nimbus.repository.UserRepository;
import edu.sharif.nimbus.service.CountryService;
import edu.sharif.nimbus.service.UserService;
import edu.sharif.nimbus.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
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
    CountryRepository countryRepository(RestTemplate restTemplate, @Value("${ninja.api.token}") String ninjaToken) {
        return new CountryRepository(restTemplate, ninjaToken);
    }

    @Bean
    CountryService countryService(CountryRepository countryRepository, UserRepository userRepository) {
        return new CountryService(countryRepository, userRepository);
    }

    @Bean
    WeatherService weatherService(CountryRepository countryRepository, UserRepository userRepository) {
        return new WeatherService(countryRepository, userRepository);
    }

    @Bean
    UserService userService(UserRepository userRepository, @Value("${admin.password.key}") String adminApiKey) {
        return new UserService(userRepository, adminApiKey);
    }

    @Bean
    UserRepository userRepository() {
        return new UserRepository();
    }

}
