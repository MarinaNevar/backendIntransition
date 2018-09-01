package by.Coursepro.course.dto.instruction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostInfo {
    private Long id;
    private String name;
    private String description;
    private String publishDate;
    private String userImage;
    private long id_user;
    private float value_rating;
    private String authorName;
}
