package edu.sharif.nimbus.model.dto.user;


import edu.sharif.nimbus.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {
    private final String username;
    private final String password;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
