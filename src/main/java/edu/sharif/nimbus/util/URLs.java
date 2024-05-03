package edu.sharif.nimbus.util;

public enum URLs {
    COUNTRIES("https://countriesnow.space/api/v0.1/countries"),
    COUNTRY("https://api.api-ninjas.com/v1/country?name="),
    WEATHER("https://api.api-ninjas.com/v1/weather?city=");
    public final String url;

    URLs(String url) {
        this.url = url;
    }
}
