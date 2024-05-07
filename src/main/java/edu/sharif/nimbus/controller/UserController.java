package edu.sharif.nimbus.controller;

import edu.sharif.nimbus.model.dto.ListDto;
import edu.sharif.nimbus.model.dto.user.UserDto;
import edu.sharif.nimbus.model.dto.user.UsernamePasswordDto;
import edu.sharif.nimbus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/admin/users")
    public ListDto<UserDto> getAllRegisteredUsers(
            @RequestHeader("Authorization") String authorization
    ) {
        return new ListDto<>(
                userService
                        .getAllUsers(authorization)
                        .stream()
                        .map(UserDto::new)
                        .toList()
        );
    }

    @PutMapping("/admin/users")
    public boolean activateUser(
            @RequestParam("username") String username,
            @RequestParam("active") boolean active
    ) {
        return userService.changeUserActivation(username, active);
    }

    @PostMapping("/users/register")
    public boolean registerUser(
            @RequestBody UsernamePasswordDto userInformation
    ) {
        return userService.registerUser(userInformation.toUser());
    }

    @PostMapping("/users/login")
    public boolean loginUser(
            @RequestBody UsernamePasswordDto userInformation
    ) {
        return userService.loginUser(userInformation.toUser());
    }
}
