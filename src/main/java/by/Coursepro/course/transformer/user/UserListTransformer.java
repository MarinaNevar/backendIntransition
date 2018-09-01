package by.Coursepro.course.transformer.user;

import by.Coursepro.course.dto.user.UserList;
import by.Coursepro.course.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserListTransformer {
    public UserList makeDto(final User user) {
        UserList dto = new UserList();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        dto.setBlocked(user.isBlocked());
        dto.setDeleted(user.isDeleted());
        return dto;
    }
}
