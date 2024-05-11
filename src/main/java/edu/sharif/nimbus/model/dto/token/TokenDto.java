package edu.sharif.nimbus.model.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sharif.nimbus.model.Token;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@Data
@RequiredArgsConstructor
public class TokenDto {
    @JsonProperty("name")
    private final String name;
    @JsonProperty("expire_date")
    private final String expireDateText;
    @JsonProperty("token")
    private final String token;

    public TokenDto(Token token) {
        this.name = token.getName();
        this.expireDateText = token.getExpirationDate() == null ? "inf" : new Date(token.getExpirationDate()).toString();
        this.token = token.getValue();
    }
}
