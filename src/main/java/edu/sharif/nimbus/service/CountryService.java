package edu.sharif.nimbus.service;

import edu.sharif.nimbus.model.Country;
import edu.sharif.nimbus.model.dto.country.CountryName;
import edu.sharif.nimbus.repository.CountryRepository;
import edu.sharif.nimbus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    final CountryRepository countryRepository;
    final UserRepository userRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository, UserRepository userRepository) {
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
    }

    public CountryName[] getAllCountriesNames(String authorization) {
        userRepository.authorizeUser(authorization);
        return countryRepository.getAllCountriesNames().toArray(CountryName[]::new);
    }

    public Country getCountryByName(String name, String authorization) {
        userRepository.authorizeUser(authorization);
        return countryRepository.getCountryByName(name);
    }
}
