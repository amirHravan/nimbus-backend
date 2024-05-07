package edu.sharif.nimbus.model.dto.user;


import edu.sharif.nimbus.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsernamePasswordDto {
    private final String username;
    private final String password;

    public User toUser() {
        return new User(username, password);
    }
}
