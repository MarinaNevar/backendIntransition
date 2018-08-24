package by.Coursepro.course.Service;

import by.Coursepro.course.DTO.CommentDTO.CommentAddDto;
import by.Coursepro.course.DTO.CommentDTO.CommentShowDto;
import by.Coursepro.course.DTO.InstructionDTO.PublicInfoDto;
import by.Coursepro.course.DTO.LikeDTO.LikeDto;
import by.Coursepro.course.DTO.RatingDTO.RatingSetDto;
import by.Coursepro.course.DTO.StepDTO.StepAddDto;
import by.Coursepro.course.DTO.StepDTO.StepEditDto;
import by.Coursepro.course.DTO.StepDTO.StepShowDto;
import by.Coursepro.course.Entity.*;
import by.Coursepro.course.Repository.*;
import by.Coursepro.course.Service.Transformer.CommentTransformer.CommentAddTransformer;
import by.Coursepro.course.Service.Transformer.CommentTransformer.CommentShowTransformer;
import by.Coursepro.course.Service.Transformer.InstructionTransformer.InstructionEditDtoTransformer;
import by.Coursepro.course.Service.Transformer.InstructionTransformer.PublicationInfoDtoTransformer;
import by.Coursepro.course.Service.Transformer.LikeTransformer.LikeDtoTransformer;
import by.Coursepro.course.Service.Transformer.RatingTransformer.RatingSetDtoTransformer;
import by.Coursepro.course.Service.Transformer.StepTransformer.StepAddTransformer;
import by.Coursepro.course.Service.Transformer.StepTransformer.StepEditDtoTransformer;
import by.Coursepro.course.Service.Transformer.StepTransformer.StepShowDtoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InstructionService {

    private final InstructionRepository instructionRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final CommentShowTransformer commentShowTransformer;
    private final LikeDtoTransformer likeDtoTransformer;
    private final RatingSetDtoTransformer ratingSetDtoTransformer;
    private final StepAddTransformer stepAddTransformer;
    private final StepShowDtoTransformer stepShowDtoTransformer;
    private final PublicationInfoDtoTransformer publicationInfoDtoTransformer;
    private final InstructionEditDtoTransformer instructionEditDtoTransformer;
    private final CommentAddTransformer commentAddTransformer;
    private final StepRepository stepRepository;
    private final CategoriesService categoriesService;
    private final StepEditDtoTransformer stepEditDtoTransformer;


    public List<PublicInfoDto> getInstructions(){
        return publicationInfoDtoTransformer.makeListDto(instructionRepository.findAll());
    }

    public void addInstruction(PublicInfoDto publicInfoDto){
        Instruction instr = publicationInfoDtoTransformer.makeModel(publicInfoDto);
        instr.setPublishDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        instr.setSteps(publicInfoDto.getSteps());
        instr.setCategories(this.categoriesService.getCategories(publicInfoDto.getCategories()));
        this.instructionRepository.save(instr);
    }

    public void addStep(StepAddDto stepAddDto){
        Instruction step = instructionRepository.findById(stepAddDto.getId_instruction());
        Set<Step>steps=new HashSet<>(step.getSteps());
        steps.add(this.stepAddTransformer.makeModel(stepAddDto));
        this.instructionRepository.save(step);
    }


    public void editStep(StepEditDto stepEditDto){
        Step step = stepEditDtoTransformer.makeEditModel(stepEditDto);
        step.setPublishDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        stepRepository.save(step);
    }

    public List<StepShowDto>getSteps(Long instruction){
        return stepShowDtoTransformer.makeListDto(stepRepository.findAllByInstruction(instruction));
    }

    public List<PublicInfoDto> getInstructionByUserId(long id){
        return publicationInfoDtoTransformer.makeListDto(instructionRepository.findAllByUser(userRepository.findById(id)));
    }
    public PublicInfoDto getPublicationById(long idPub){
        return publicationInfoDtoTransformer.makeDto(instructionRepository.findById(idPub));
    }

    public void editInstruction(PublicInfoDto publicInfoDto){
        Instruction instruction = instructionEditDtoTransformer.makeEditModel(publicInfoDto);
        instruction.setPublishDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        instructionRepository.save(instruction);
    }
    public void deleteInstruction(long id){
        Instruction deleteInstr = instructionRepository.findById(id);
        deleteInstr.setCategories(null);
        instructionRepository.save(deleteInstr);
        instructionRepository.delete(deleteInstr);

    }
    public void addComment(CommentAddDto commentAddDto){
        Instruction post = this.instructionRepository.findById(commentAddDto.getId_instruction());
        Set<Comment> comments=new HashSet<>(post.getComments());
        comments.add(this.commentAddTransformer.makeModel(commentAddDto));
        post.setComments(comments);
        this.instructionRepository.save(post);
    }
    public Set<CommentShowDto> getPostComment(long idInstr){
        return commentShowTransformer.makeSetDto(instructionRepository.findById(idInstr).getComments());
    }

    public void addLike(LikeDto likeDto){
        Like like = this.likeDtoTransformer.makeModel(likeDto);
        Comment comment = like.getComment();
        User user = like.getUser();
        Set<Like> likes = new HashSet<>(user.getLikes());
        boolean isExist = false;
        Like deleteLike = new Like();
        for(Like sLike : likes) {
            if(sLike.getUser().getId().equals(user.getId())
                    && sLike.getComment().getId().equals(comment.getId())) {
                deleteLike = sLike;
                isExist = true;
            }
        }
        if (!isExist) {
            likes.add(like);
            comment.getUser().setAmountLike(comment.getUser().getAmountLike() + 1);
        } else {
            likes.remove(deleteLike);
            comment.getUser().setAmountLike(comment.getUser().getAmountLike() - 1);
        }
        comment.setLikes(likes);
        user.setLikes(likes);
        if (deleteLike.getId() != null) {
            likeRepository.delete(deleteLike);
        }
        commentRepository.save(comment);
        userRepository.save(user);
    }
    public float setPostRating(RatingSetDto ratingSetDto){
        Instruction instr = instructionRepository.findById(ratingSetDto.getIdPost());
        Boolean newRating = true;
        for(Rating rating : instr.getRating()) {
            if(rating.getUser().getUsername().equals(ratingSetDto.getUsername())) {
                newRating = false;
                rating.setValue(ratingSetDto.getRating());
            }
        }
        if(newRating) {
            instr.getRating().add(ratingSetDtoTransformer.makeModel(ratingSetDto));
        }
        instr.setRatingValue(currentRating(instr.getRating()));
        instructionRepository.save(instr);
        return  instr.getRatingValue();

    }
    private float currentRating(Set<Rating> ratings) {
        float currentRating = 0;
        for(Rating rating : ratings) {
            currentRating += rating.getValue();
        }
        return currentRating / ratings.size();
    }
    public float getPostRating(long id) {
        return instructionRepository.findById(id).getRatingValue();
    }
    public void deleteLikes(Set<Like> likes) {
        for(Like like : likes) {
            User user = like.getComment().getUser();
            user.setAmountLike(user.getAmountLike() - 1);
        }
    }
    public Set<PublicInfoDto> getNewsByIdUsername(String username) {
        return publicationInfoDtoTransformer.makeSetDto(userRepository.findByUsername(username).getInstructions());
    }


}
