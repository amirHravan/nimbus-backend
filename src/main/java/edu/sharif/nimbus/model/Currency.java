package edu.sharif.nimbus.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Currency {
    private final String code;
    private final String name;
}
