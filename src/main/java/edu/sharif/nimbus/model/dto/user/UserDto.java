package edu.sharif.nimbus.model.dto.user;


import edu.sharif.nimbus.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UserDto {
    private final String username;
    private final String password;
    private final LocalDateTime registrationDate;
    private final Boolean active;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.registrationDate = user.getRegistrationDate();
        this.active = user.isActive();
    }
}
