package by.Coursepro.course.Controller;

import by.Coursepro.course.DTO.ErrorDto;
import by.Coursepro.course.DTO.UserDTO.AuthUserDto;
import by.Coursepro.course.DTO.LoginDTO.LoginRequestDto;
import by.Coursepro.course.DTO.LoginDTO.LoginResponseDto;
import by.Coursepro.course.DTO.UserDTO.UserAddDto;
import by.Coursepro.course.Service.AuthService;
import by.Coursepro.course.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authenticationService.login(loginRequestDto);
    }

    @PostMapping("/registration")
    public ErrorDto addUser(@RequestBody UserAddDto userAddDto) {
        return userService.addUser(userAddDto);
    }

}