package by.Coursepro.course.transformer.user;

import by.Coursepro.course.dto.user.UserEdit;
import by.Coursepro.course.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEditTranformer {


    public UserEdit makeDto(final User user){
        UserEdit dto=new UserEdit();
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

    public User mergeUserEdit(User user, UserEdit userEdit){
        user.setUsername(userEdit.getUsername());
        user.setFirstName(userEdit.getFirstName());
        user.setLastName(userEdit.getLastName());
        user.setTheme(userEdit.getTheme());
        user.setRole(userEdit.getRole());
        user.setLanguage(userEdit.getLanguage());
        user.setBlocked(userEdit.isBlocked());
        user.setDeleted(userEdit.isDeleted());
        return user;
    }
}
