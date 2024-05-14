package edu.sharif.nimbus.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
public class Token {
    private final String name;
    private final String value;
    private final LocalDateTime expirationDate;

}
