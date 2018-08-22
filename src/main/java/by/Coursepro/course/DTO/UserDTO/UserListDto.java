package by.Coursepro.course.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserListDto {
    private long id;
    private String username;
    private String email;
    private String role;
    private boolean isBlocked;
    private boolean isDeleted;
}
