package by.Coursepro.course.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserList {
    private long id;
    private String username;
    private String email;
    private String role;
    private boolean isBlocked;
    private boolean isDeleted;
}
