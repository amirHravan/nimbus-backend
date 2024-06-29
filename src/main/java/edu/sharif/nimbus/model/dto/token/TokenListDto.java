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
        this.tokenDTOs = tokenDTOs.stream().map(tokenDto -> new TokenDto(tokenDto.getName(), tokenDto.getExpireDateText(), tokenDto.getToken())).toList();
        this.count = tokenDTOs.size();
    }

    public TokenListDto(List<TokenDto> tokenDTOs, int page, int limit) {
        int end = Math.min(page * limit, tokenDTOs.size());
        int start = Math.min((page - 1) * limit, end);
        this.count = tokenDTOs.size();
        this.tokenDTOs = tokenDTOs.subList(start, end);
    }
}
