package edu.sharif.nimbus;


import edu.sharif.nimbus.model.Country;
import edu.sharif.nimbus.model.Currency;
import edu.sharif.nimbus.model.User;
import edu.sharif.nimbus.model.dto.country.CountryName;
import edu.sharif.nimbus.repository.CountryRepository;
import edu.sharif.nimbus.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CountryRepository countryRepository;
    @MockBean
    private UserRepository userRepository;


    @BeforeAll
    static void setup() {

    }

    private void mockRequiredMethods() {
        when(countryRepository.getAllCountriesNames()).thenReturn(new ArrayList<>(List.of(new CountryName("Iran"), new CountryName("USA"))));
        when(countryRepository.getCountryByName("Iran")).thenReturn(new Country("Iran", "Tehran", "Asia", 1000.0, -2.0f, new Currency("IRR", "Iranian Rial")));
        when(countryRepository.getCountryByName("USA")).thenReturn(new Country("USA", "Washington", "North America", 1500.0, 2.0f, new Currency("USD", "US Dollar")));
        when(userRepository.authorizeUser("test")).thenReturn(new User("test", "test"));
    }

    @Test
    void getAllCountriesNoAuthenticationTest() throws Exception {
        mockRequiredMethods();
        this.mockMvc.perform(get("/countries"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getAllCountryNamesTest() throws Exception {
        mockRequiredMethods();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "test");
        this.mockMvc.perform(get("/countries").headers(headers))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getCountryTest() throws Exception {
        mockRequiredMethods();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "test");
        this.mockMvc.perform(get("/countries/Iran").headers(headers))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Iran")));
    }
}
