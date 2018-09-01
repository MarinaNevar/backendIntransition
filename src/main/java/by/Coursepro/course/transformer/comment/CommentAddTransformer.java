package by.Coursepro.course.transformer.comment;

import by.Coursepro.course.dto.comment.CommentAdd;
import by.Coursepro.course.entity.Comment;
import by.Coursepro.course.repository.InstructionRepository;
import by.Coursepro.course.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class CommentAddTransformer {
    private final UserRepository userRepository;
    private final InstructionRepository instructionRepository;

    public Comment makeModel(CommentAdd commentAddDto){
        Comment comment = new Comment();
        comment.setText(commentAddDto.getText());
        comment.setUser(userRepository.findByUsername(commentAddDto.getUsername()));
        comment.setInstruction(instructionRepository.findById(commentAddDto.getId_instruction()));
        comment.setLikes(new HashSet<>());
        comment.setPublish_date(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return comment;
    }
}
