package by.Coursepro.course.DTO.LoginDTO;

import by.Coursepro.course.DTO.ErrorDto;
import by.Coursepro.course.Entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LoginResponseDto {

    private String token;
    private ErrorDto errorDto;
    private long userId;
    private String username;
    private String userRole;
    private String themeName;
    private String languageName;


    public LoginResponseDto(String token, ErrorDto errorDto, User user) {
        this.token = token;
        this.errorDto = errorDto;
        if (user != null) {
            this.userId = user.getId();
            this.username = user.getUsername();
            this.userRole = user.getRole().toString();
            this.themeName = user.getTheme().getName();
            this.languageName = user.getLanguage().getName();
        }
    }


}
