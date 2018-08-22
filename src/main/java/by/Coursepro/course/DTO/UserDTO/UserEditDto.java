package by.Coursepro.course.DTO.UserDTO;

import by.Coursepro.course.Entity.Language;
import by.Coursepro.course.Entity.Role;
import by.Coursepro.course.Entity.Theme;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEditDto {

    private Long id;
    private String username;
    private Role role;
    private String firstName;
    private String lastName;
    private String avatar;
    private boolean isDeleted;
    private boolean isBlocked;
    private Theme theme;
    private Language language;
}
