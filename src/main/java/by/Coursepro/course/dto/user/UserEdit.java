package by.Coursepro.course.dto.user;

import by.Coursepro.course.entity.Language;
import by.Coursepro.course.entity.Role;
import by.Coursepro.course.entity.Theme;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEdit {

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
