package by.Coursepro.course.Service.Transformer.UserTransform;

import by.Coursepro.course.DTO.UserDTO.UserEditDto;
import by.Coursepro.course.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEditTranformer {


    public UserEditDto makeDto(final User user){
        UserEditDto dto=new UserEditDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setTheme(user.getTheme());
        dto.setAvatar(user.getAvatar());
        dto.setRole(user.getRole());
        dto.setDeleted(user.isDeleted());
        dto.setBlocked(user.isBlocked());
        return dto;
    }

    public User mergeUserEdit(User user, UserEditDto userEditDto){
        user.setUsername(userEditDto.getUsername());
        user.setFirstName(userEditDto.getFirstName());
        user.setLastName(userEditDto.getLastName());
        user.setTheme(userEditDto.getTheme());
        user.setRole(userEditDto.getRole());
        user.setLanguage(userEditDto.getLanguage());
        user.setBlocked(userEditDto.isBlocked());
        user.setDeleted(userEditDto.isDeleted());
        return user;
    }
}
