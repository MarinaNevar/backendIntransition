package by.Coursepro.course.DTO.CommentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentAddDto {
    private String text;
    private long id_instruction;
    private String username;

}
