package edu.sharif.nimbus;

import edu.sharif.nimbus.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class NimbusApplicationTests {

	@Autowired
	private CountryController countryController;

	@Test
	void contextLoads() {
		assertThat(countryController).isNotNull();
	}

}
