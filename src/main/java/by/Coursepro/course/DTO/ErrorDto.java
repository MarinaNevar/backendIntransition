package by.Coursepro.course.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String error;
    private String message;

    public ErrorDto(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
