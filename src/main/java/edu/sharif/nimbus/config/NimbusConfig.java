package edu.sharif.nimbus.config;

import edu.sharif.nimbus.repository.UserRepository;
import edu.sharif.nimbus.service.CountryService;
import edu.sharif.nimbus.service.UserService;
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
    CountryService countryService(UserService userService, RestTemplate restTemplate, @Value("${ninja.api.token}") String ninjaToken) {
        return new CountryService(userService, restTemplate, ninjaToken);
    }

    @Bean
    UserService userService(UserRepository userRepository, @Value("${admin.password.key}") String adminApiKey) {
        return new UserService(userRepository, adminApiKey);
    }

}
