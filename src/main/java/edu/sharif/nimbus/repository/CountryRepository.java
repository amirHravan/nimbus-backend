package edu.sharif.nimbus.repository;

import edu.sharif.nimbus.model.Country;
import edu.sharif.nimbus.model.dto.CountryName;
import edu.sharif.nimbus.repository.dto.RemoteCountryDto;
import edu.sharif.nimbus.repository.dto.RemoteCountryNameListDto;
import edu.sharif.nimbus.util.URLs;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CountryRepository {

    final RestTemplate restTemplate;

    public CountryRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ArrayList<CountryName> getAllCountryNames() {
        RemoteCountryNameListDto data = restTemplate.getForObject(URLs.COUNTRIES.url, RemoteCountryNameListDto.class);
        assert data != null;
        return data.getCountryNames();
    }

    public Country getCountryByName(String name) {
        RemoteCountryDto data = restTemplate.getForObject(URLs.COUNTRY.url + name, RemoteCountryDto.class);
        assert data != null;
        return data.toCountry(name);
    }
}
