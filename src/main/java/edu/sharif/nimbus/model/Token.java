package edu.sharif.nimbus.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Token {
    private final String name;
    private final String value;
    private final Long expirationDate;
}
