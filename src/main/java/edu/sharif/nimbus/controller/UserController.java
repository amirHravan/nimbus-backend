package edu.sharif.nimbus.controller;

import edu.sharif.nimbus.model.dto.ListDto;
import edu.sharif.nimbus.model.dto.ResultDto;
import edu.sharif.nimbus.model.dto.token.NameAndExpireDateDto;
import edu.sharif.nimbus.model.dto.token.TokenDto;
import edu.sharif.nimbus.model.dto.token.TokenListDto;
import edu.sharif.nimbus.model.dto.user.UserDto;
import edu.sharif.nimbus.model.dto.user.UsernamePasswordDto;
import edu.sharif.nimbus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/admin/users")
    public ListDto<UserDto> getAllRegisteredUsers(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit
    ) {
        return new ListDto<>(
                userService
                        .getAllUsers(authorization)
                        .stream()
                        .map(UserDto::new)
                        .toList()
                , page, limit
        );
    }

    @PutMapping("/admin/users")
    public boolean activateUser(
            @RequestParam("username") String username,
            @RequestParam("active") boolean active,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        return userService.changeUserActivation(authorization, username, active);
    }

    @PostMapping("/users/register")
    public boolean registerUser(
            @RequestBody UsernamePasswordDto userInformation
    ) {
        return userService.registerUser(userInformation.getUsername(), userInformation.getPassword());
    }

    @PostMapping("/users/login")
    public TokenDto loginUser(
            @RequestBody UsernamePasswordDto userInformation
    ) {
        return new TokenDto(userService.loginUser(userInformation.getUsername(), userInformation.getPassword()));
    }

    @PostMapping("/user/api-tokens")
    public TokenDto addToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestBody NameAndExpireDateDto tokenInformation
    ) {
        return new TokenDto(userService.addToken(authorization, tokenInformation.getName(), tokenInformation.getExpirationDate()));
    }

    @DeleteMapping("/user/api-tokens")
    public ResultDto deleteToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        userService.deleteToken(authorization);
        return new ResultDto(true, "deleted");
    }

    @GetMapping("/user/api-tokens")
    public TokenListDto getTokenList(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit
    ) {
        return new TokenListDto(userService.getTokens(authorization).stream().map(TokenDto::new).toList(), page, limit);
    }


}
