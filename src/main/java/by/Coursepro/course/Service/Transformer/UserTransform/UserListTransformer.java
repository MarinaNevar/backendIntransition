package by.Coursepro.course.Service.Transformer.UserTransform;

import by.Coursepro.course.DTO.UserDTO.UserListDto;
import by.Coursepro.course.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserListTransformer {
    public UserListDto makeDto(final User user) {
        UserListDto dto = new UserListDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        dto.setBlocked(user.isBlocked());
        dto.setDeleted(user.isDeleted());
        return dto;
    }
}
