package edu.sharif.nimbus.model.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.sharif.nimbus.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsernamePasswordDto {
    @JsonProperty("username")
    private final String username;
    @JsonProperty("password")
    private final String password;

}
