package edu.sharif.nimbus;


import edu.sharif.nimbus.model.User;
import edu.sharif.nimbus.service.UserService;
import edu.sharif.nimbus.util.URLs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @MockBean
    private UserService userService;


    @BeforeEach
    public void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    private void mockRequiredMethods() {
        when(userService.authorizeUser("test")).thenReturn(new User("test", "test"));
    }

    @Test
    void getAllCountriesNoAuthenticationTest() throws Exception {
        mockRequiredMethods();

        mockServer.expect(requestTo(URLs.COUNTRIES.url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"success\": false, \"message\": \"nothing!\", \"data\": [{\"iso3\": \"IRR\", \"iso2\": \"IR\", \"country\": \"Iran\"}, {\"iso3\": \"USA\", \"iso2\": \"US\", \"country\": \"United States\"}]}", MediaType.APPLICATION_JSON));

        this.mockMvc.perform(get("/countries"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getAllCountryNamesTest() throws Exception {
        mockRequiredMethods();

        mockServer.expect(requestTo(URLs.COUNTRIES.url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"success\": false, \"message\": \"nothing!\", \"data\": [{\"iso3\": \"IRR\", \"iso2\": \"IR\", \"country\": \"Iran\"}, {\"iso3\": \"USA\", \"iso2\": \"US\", \"country\": \"United States\"}]}", MediaType.APPLICATION_JSON));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "test");
        this.mockMvc.perform(get("/countries").headers(headers))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getCountryTest() throws Exception {
        mockRequiredMethods();

        mockServer.expect(requestTo(URLs.COUNTRY.url + "Iran"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[{\"gdp\": 473091.0, \"sex_ratio\": 102.0, \"surface_area\": 1628750.0, \"life_expectancy_male\": 75.3, \"unemployment\": 11.2, \"imports\": 20502.0, \"homicide_rate\": 2.5, \"currency\": {\"code\": \"IRR\", \"name\": \"Iranian Rial\"}, \"iso2\": \"IR\", \"employment_services\": 51.9, \"employment_industry\": 30.3, \"urban_population_growth\": 2.0, \"secondary_school_enrollment_female\": 84.7, \"employment_agriculture\": 17.8, \"capital\": \"Tehran\", \"co2_emissions\": 567.1, \"forested_area\": 6.6, \"tourists\": 7295.0, \"exports\": 17394.0, \"life_expectancy_female\": 77.6, \"post_secondary_enrollment_female\": 62.9, \"post_secondary_enrollment_male\": 73.3, \"primary_school_enrollment_female\": 113.9, \"infant_mortality\": 12.8, \"gdp_growth\": -4.8, \"threatened_species\": 161.0, \"population\": 83993.0, \"urban_population\": 75.4, \"secondary_school_enrollment_male\": 87.9, \"name\": \"Iran, Islamic Republic Of\", \"pop_growth\": 1.4, \"region\": \"Southern Asia\", \"pop_density\": 51.6, \"internet_users\": 70.0, \"gdp_per_capita\": 5783.5, \"fertility\": 2.2, \"refugees\": 979.5, \"primary_school_enrollment_male\": 107.7}]", MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo(URLs.COUNTRY.url + "United States"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[{\"gdp\": 20580223.0, \"sex_ratio\": 97.9, \"surface_area\": 9833517.0, \"life_expectancy_male\": 76.3, \"unemployment\": 3.9, \"imports\": 2567490.0, \"homicide_rate\": 5.0, \"currency\": {\"code\": \"USD\", \"name\": \"Us Dollar\"}, \"iso2\": \"US\", \"employment_services\": 79.0, \"employment_industry\": 19.7, \"urban_population_growth\": 0.9, \"secondary_school_enrollment_female\": 98.7, \"employment_agriculture\": 1.3, \"capital\": \"Washington, D.C.\", \"co2_emissions\": 4761.3, \"forested_area\": 33.9, \"tourists\": 79746.0, \"exports\": 1644280.0, \"life_expectancy_female\": 81.3, \"post_secondary_enrollment_female\": 102.0, \"post_secondary_enrollment_male\": 75.0, \"primary_school_enrollment_female\": 101.4, \"infant_mortality\": 5.8, \"gdp_growth\": 2.9, \"threatened_species\": 1655.0, \"population\": 331003.0, \"urban_population\": 82.5, \"secondary_school_enrollment_male\": 99.2, \"name\": \"United States\", \"pop_growth\": 0.6, \"region\": \"Northern America\", \"pop_density\": 36.2, \"internet_users\": 87.3, \"gdp_per_capita\": 62917.9, \"fertility\": 1.8, \"refugees\": 1043.2, \"primary_school_enrollment_male\": 102.2}]", MediaType.APPLICATION_JSON));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "test");
        this.mockMvc.perform(get("/countries/Iran").headers(headers))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Iran")));
    }

    @Test
    void getCountryWeatherTest() throws Exception {
        mockRequiredMethods();

        mockServer.expect(requestTo(URLs.COUNTRY.url + "Iran"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[{\"gdp\": 473091.0, \"sex_ratio\": 102.0, \"surface_area\": 1628750.0, \"life_expectancy_male\": 75.3, \"unemployment\": 11.2, \"imports\": 20502.0, \"homicide_rate\": 2.5, \"currency\": {\"code\": \"IRR\", \"name\": \"Iranian Rial\"}, \"iso2\": \"IR\", \"employment_services\": 51.9, \"employment_industry\": 30.3, \"urban_population_growth\": 2.0, \"secondary_school_enrollment_female\": 84.7, \"employment_agriculture\": 17.8, \"capital\": \"Tehran\", \"co2_emissions\": 567.1, \"forested_area\": 6.6, \"tourists\": 7295.0, \"exports\": 17394.0, \"life_expectancy_female\": 77.6, \"post_secondary_enrollment_female\": 62.9, \"post_secondary_enrollment_male\": 73.3, \"primary_school_enrollment_female\": 113.9, \"infant_mortality\": 12.8, \"gdp_growth\": -4.8, \"threatened_species\": 161.0, \"population\": 83993.0, \"urban_population\": 75.4, \"secondary_school_enrollment_male\": 87.9, \"name\": \"Iran, Islamic Republic Of\", \"pop_growth\": 1.4, \"region\": \"Southern Asia\", \"pop_density\": 51.6, \"internet_users\": 70.0, \"gdp_per_capita\": 5783.5, \"fertility\": 2.2, \"refugees\": 979.5, \"primary_school_enrollment_male\": 107.7}]", MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo(URLs.WEATHER.url + "Tehran"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"wind_degrees\": 25.0, \"humidity\": 50.0, \"wind_speed\": 10.0, \"temp\": \"25\"}}", MediaType.APPLICATION_JSON));


        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "test");
        this.mockMvc.perform(get("/countries/Iran/weather").headers(headers))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Iran")));
    }
}
