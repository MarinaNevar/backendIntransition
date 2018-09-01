package by.Coursepro.course.transformer.like;

import by.Coursepro.course.dto.like.LikeEdit;
//import by.Coursepro.course.entity.Like;
import by.Coursepro.course.entity.Like;
import by.Coursepro.course.repository.CommentRepository;
import by.Coursepro.course.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class LikeDtoTransformer {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public Like makeModel(LikeEdit likeEdit) {
        Like like = new Like();
        like.setId(likeEdit.getId());
        like.setUser(this.userRepository.findById(likeEdit.getId_user()));
        like.setComment(this.commentRepository.findById(likeEdit.getId_comment()));
        return like;
    }

    public LikeEdit makeDto(Like like) {
        LikeEdit likeEdit = new LikeEdit();
        likeEdit.setId(like.getId());
        likeEdit.setId(like.getComment().getId());
        likeEdit.setId_user(like.getUser().getId());
        return likeEdit;
    }

    public Set<LikeEdit> makeSetLikeDto(Set<Like> likeSet) {
        Set<LikeEdit> likeEditSet = new HashSet<>();
        for (Like like : likeSet) {
            likeEditSet.add(makeDto(like));
        }
        return likeEditSet;
    }
}
