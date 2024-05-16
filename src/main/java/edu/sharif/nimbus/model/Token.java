package edu.sharif.nimbus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Token {
    private final String name;
    @Id
    private final String value;
    private final LocalDateTime expirationDate;

}
