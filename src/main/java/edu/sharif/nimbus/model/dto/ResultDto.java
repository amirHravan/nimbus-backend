package edu.sharif.nimbus.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResultDto {
    private final boolean isSuccessful;
    private final String message;
}
