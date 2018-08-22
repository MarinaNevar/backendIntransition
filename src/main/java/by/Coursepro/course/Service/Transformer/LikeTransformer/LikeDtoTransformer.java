package by.Coursepro.course.Service.Transformer.LikeTransformer;

import by.Coursepro.course.DTO.CommentDTO.CommentAddDto;
import by.Coursepro.course.DTO.LikeDTO.LikeDto;
import by.Coursepro.course.Entity.Comment;
import by.Coursepro.course.Entity.Like;
import by.Coursepro.course.Repository.CommentRepository;
import by.Coursepro.course.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class LikeDtoTransformer {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public Like makeModel(LikeDto likeDto) {
        Like like = new Like();
        like.setId(likeDto.getId());
        like.setUser(this.userRepository.findById(likeDto.getId_user()));
        like.setComment(this.commentRepository.findById(likeDto.getId_comment()));
        return like;
    }

    public LikeDto makeDto(Like like) {
        LikeDto likeDto = new LikeDto();
        likeDto.setId(like.getId());
        likeDto.setId(like.getComment().getId());
        likeDto.setId_user(like.getUser().getId());
        return likeDto;
    }

    public Set<LikeDto> makeSetLikeDto(Set<Like> likeSet) {
        Set<LikeDto> likeDtoSet = new HashSet<>();
        for (Like like : likeSet) {
            likeDtoSet.add(makeDto(like));
        }
        return likeDtoSet;
    }
}
