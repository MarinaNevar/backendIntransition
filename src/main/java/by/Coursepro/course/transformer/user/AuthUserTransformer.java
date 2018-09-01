package by.Coursepro.course.transformer.user;

import by.Coursepro.course.dto.user.AuthUser;
import by.Coursepro.course.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUserTransformer {

    public AuthUser makeDto(final User user) {
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setRole(user.getRole().name());
        return authUser;
    }

}