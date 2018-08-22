package by.Coursepro.course.DTO.CommentDTO;

import by.Coursepro.course.DTO.LikeDTO.LikeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CommentShowDto {
    private long id;
    private String text;
    private String author_name;
    private String publish_date;
    private String avatar;
    private long current_like_user;
    private Set<LikeDto> likes;
}
