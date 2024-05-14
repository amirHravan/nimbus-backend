package edu.sharif.nimbus.service;

import edu.sharif.nimbus.model.Country;
import edu.sharif.nimbus.model.dto.country.CountryName;
import edu.sharif.nimbus.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    final CountryRepository countryRepository;
    final UserService userService;

    @Autowired
    public CountryService(CountryRepository countryRepository, UserService userService) {
        this.countryRepository = countryRepository;
        this.userService = userService;
    }

    public CountryName[] getAllCountriesNames(String authorization) {
        userService.authorizeUser(authorization);
        return countryRepository.getAllCountriesNames().toArray(CountryName[]::new);
    }

    public Country getCountryByName(String name, String authorization) {
        userService.authorizeUser(authorization);
        return countryRepository.getCountryByName(name);
    }
}
