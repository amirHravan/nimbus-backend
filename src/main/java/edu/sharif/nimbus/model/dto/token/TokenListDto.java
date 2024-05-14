package edu.sharif.nimbus.model.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TokenListDto {
    @JsonProperty("tokens")
    private final List<TokenDto> tokenDTOs;
    @JsonProperty("count")
    private final int count;

    public TokenListDto(List<TokenDto> tokenDTOs) {
        this.tokenDTOs = tokenDTOs.stream().map(tokenDto -> new TokenDto(tokenDto.getName(), tokenDto.getExpireDateText(), "API ***")).toList();
        this.count = tokenDTOs.size();
    }
}
