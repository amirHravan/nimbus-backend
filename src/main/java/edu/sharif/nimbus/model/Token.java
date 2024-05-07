package edu.sharif.nimbus.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@Data
@RequiredArgsConstructor
public class Token {
    private final String value;
    private final Date expirationDate;
}
