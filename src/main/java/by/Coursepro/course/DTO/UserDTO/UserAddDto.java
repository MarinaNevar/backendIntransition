package by.Coursepro.course.DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDto {
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String email;

}
