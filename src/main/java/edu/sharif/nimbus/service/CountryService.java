package edu.sharif.nimbus.service;

import edu.sharif.nimbus.model.Country;
import edu.sharif.nimbus.model.dto.CountryName;
import edu.sharif.nimbus.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryName[] getAllCountryNames() {
        return countryRepository.getAllCountryNames().toArray(CountryName[]::new);
    }

    public Country getCountryByName(String name) {
        return countryRepository.getCountryByName(name);
    }
}
