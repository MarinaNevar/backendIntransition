package by.Coursepro.course.dto.comment;

import by.Coursepro.course.dto.like.LikeEdit;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CommentShow {
    private long id;
    private String text;
    private String author_name;
    private String publish_date;
    private String avatar;
    private long current_like_user;
    private Set<LikeEdit> likes;
}
