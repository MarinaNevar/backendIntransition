package by.Coursepro.course.Service.Transformer.CommentTransformer;

import by.Coursepro.course.DTO.CommentDTO.CommentAddDto;
import by.Coursepro.course.Entity.Comment;
import by.Coursepro.course.Repository.InstructionRepository;
import by.Coursepro.course.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CommentAddTransformer {
    private final UserRepository userRepository;
    private final InstructionRepository instructionRepository;
    public Comment makeModel(CommentAddDto commentAddDto){
        Comment comment = new Comment();
        comment.setText(commentAddDto.getText());
        comment.setUser(userRepository.findByUsername(commentAddDto.getUsername()));
        comment.setInstruction(instructionRepository.findById(commentAddDto.getId_instruction()));
        comment.setPublish_date(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return comment;
    }
}
