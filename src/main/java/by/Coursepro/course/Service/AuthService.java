package by.Coursepro.course.Service;

import by.Coursepro.course.Constants.Abbreviation;
import by.Coursepro.course.DTO.*;
import by.Coursepro.course.DTO.LoginDTO.LoginRequestDto;
import by.Coursepro.course.DTO.LoginDTO.LoginResponseDto;
import by.Coursepro.course.DTO.UserDTO.AuthUserDto;
import by.Coursepro.course.Entity.User;
import by.Coursepro.course.Repository.UserRepository;
import by.Coursepro.course.Security.Model.JwtUserDetails;
import by.Coursepro.course.Security.SecurityHelper;
import by.Coursepro.course.Security.Service.AuthenticationHelper;
import by.Coursepro.course.Service.Transformer.UserTransform.AuthUserTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthUserTransformer authUserTransformer;
    private final AuthenticationHelper authenticationHelper;
    private final AuthenticationManager authenticationManager;


    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        try {
            String username = Optional.ofNullable(loginRequestDto.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed."));

            String password = Optional.ofNullable(loginRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed."));

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
                    password);

            // Try to authenticate with this token
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);

            // Set generated JWT token to response header
            if (authResult.isAuthenticated()) {
                JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();

                Optional<User> user = userRepository.findById(userDetails.getId());
                if (Objects.isNull(user)) {
                    throw new JsonException("User not exist in system.");
                }

                String token = this.authenticationHelper.generateToken(userDetails.getId());

                return new LoginResponseDto(token,  new ErrorDto(Abbreviation.SUCCESS_AUTHENTICATION, Abbreviation.SUCCESS), user.get() );
            } else {
                throw new JsonException("Authentication failed.");
            }

        } catch (BadCredentialsException exception) {
            return new LoginResponseDto(null, new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_INVALID_PARAMETRS), null);
        }
    }

    @Transactional(readOnly = true)
    public AuthUserDto getMe() {
        Authentication authentication = SecurityHelper.getAuthenticationWithCheck();
        User byUsername = userRepository.findByUsername(authentication.getName());

        return authUserTransformer.makeDto(byUsername);
    }
}
