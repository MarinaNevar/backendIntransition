package by.Coursepro.course.transformer.comment;

import by.Coursepro.course.dto.comment.CommentShow;
import by.Coursepro.course.entity.Comment;
import by.Coursepro.course.transformer.like.LikeDtoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentShowTransformer {
    private final LikeDtoTransformer likeDtoTransformer;

    public CommentShow makeDto(Comment comment){
        CommentShow commentShow = new CommentShow();
        commentShow.setId(comment.getId());
        commentShow.setText(comment.getText());
        commentShow.setAuthor_name(comment.getUser().getUsername());
        commentShow.setLikes(likeDtoTransformer.makeSetLikeDto(comment.getLikes()));
        commentShow.setPublish_date(comment.getPublish_date());
        commentShow.setCurrent_like_user(comment.getUser().getAmountLike());
        commentShow.setAvatar(comment.getUser().getAvatar());
        return commentShow;
    }
    public List<CommentShow> makeListDto(List<Comment> comments) {
        List<CommentShow> commentsShowDto = new ArrayList<>();
        for (Comment comment : comments) {
            commentsShowDto.add(makeDto(comment));
        }
        return commentsShowDto;
    }
}
