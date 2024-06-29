package edu.sharif.nimbus.config;

import edu.sharif.nimbus.model.Token;
import edu.sharif.nimbus.model.User;
import edu.sharif.nimbus.repository.UserRepository;
import edu.sharif.nimbus.service.CountryService;
import edu.sharif.nimbus.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

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
        User admin = new User("admin", "admin");
        admin.setActive(true);
        admin.setMainToken(new Token("admin", adminApiKey, LocalDateTime.MAX));

        User user1 = new User("amir1", "1234");
        User user2 = new User("amir2", "1234");
        User user3 = new User("amir3", "1234");
        User user4 = new User("amir4", "1234");
        User user5 = new User("amir5", "1234");
        User user6 = new User("amir6", "1234");
        User user7 = new User("amir7", "1234");

        user7.addToken("1", LocalDateTime.now().toString());
        user7.addToken("2", LocalDateTime.now().toString());
        user7.addToken("3", LocalDateTime.now().toString());
        user7.addToken("4", LocalDateTime.now().toString());
        user7.addToken("5", LocalDateTime.now().toString());
        user7.addToken("5", LocalDateTime.now().toString());
        user7.addToken("6", LocalDateTime.now().toString());
        user7.addToken("7", LocalDateTime.now().toString());
        user7.addToken("8", LocalDateTime.now().toString());

        userRepository.saveAll(List.of(user1, user2, user3, user4, user5, user6, user7));
        return new UserService(userRepository, admin);
    }

}
