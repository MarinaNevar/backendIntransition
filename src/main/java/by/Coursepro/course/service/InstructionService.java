package by.Coursepro.course.service;

import by.Coursepro.course.dto.comment.CommentAdd;
import by.Coursepro.course.dto.comment.CommentShow;
import by.Coursepro.course.dto.instruction.InstructionInfo;
import by.Coursepro.course.dto.instruction.InstructionShowInfo;
import by.Coursepro.course.dto.instruction.InstructionPresent;
import by.Coursepro.course.dto.like.LikeEdit;
import by.Coursepro.course.dto.rating.RatingSet;
import by.Coursepro.course.dto.step.StepAdd;
import by.Coursepro.course.dto.step.StepEdit;
import by.Coursepro.course.dto.step.StepShow;
import by.Coursepro.course.entity.*;
import by.Coursepro.course.repository.*;
import by.Coursepro.course.transformer.comment.CommentAddTransformer;
import by.Coursepro.course.transformer.comment.CommentShowTransformer;
import by.Coursepro.course.transformer.instruction.InstructionEditDtoTransformer;
import by.Coursepro.course.transformer.instruction.InstructionPresentTransformer;
import by.Coursepro.course.transformer.instruction.PublicationInfoDtoTransformer;
import by.Coursepro.course.transformer.like.LikeDtoTransformer;
import by.Coursepro.course.transformer.rating.RatingSetDtoTransformer;
import by.Coursepro.course.transformer.step.StepAddTransformer;
import by.Coursepro.course.transformer.step.StepEditDtoTransformer;
import by.Coursepro.course.transformer.step.StepShowDtoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private final InstructionPresentTransformer instructionPresentTransformer;
    private final CommentAddTransformer commentAddTransformer;
    private final StepRepository stepRepository;
    private final CategoriesService categoriesService;
    private final StepEditDtoTransformer stepEditDtoTransformer;

    public List<InstructionPresent> getInstructions(){
        return instructionPresentTransformer.makeListDto(instructionRepository.findAll());
    }

    public void addInstruction(InstructionInfo InstructionInfo){
        Instruction instr = publicationInfoDtoTransformer.makeModel(InstructionInfo);
        setDefaultSettings(instr);
        this.instructionRepository.save(instr);
        instr.getSteps().forEach((Step step) -> {step.setInstruction(instr);});
        this.instructionRepository.save(instr);
    }

    public void addStep(StepAdd stepAdd){
        Instruction step = instructionRepository.findById(stepAdd.getId_instruction());
        Set<Step>steps=new HashSet<>(step.getSteps());
        steps.add(this.stepAddTransformer.makeModel(stepAdd));
        this.instructionRepository.save(step);

    }
    public List<InstructionShowInfo> getInstructionByUsername(String username){
        return publicationInfoDtoTransformer.makeShowList(instructionRepository.findAllByUser(userRepository.findByUsername(username)));

    }


    public void editStep(StepEdit stepEdit){
        Step step = stepEditDtoTransformer.makeEditModel(stepEdit);
        stepRepository.save(step);
    }

    public List<StepShow>getSteps(Long instruction){
        return stepShowDtoTransformer.makeListDto(stepRepository.findAllByInstruction(instruction));
    }



    public List<InstructionInfo> getInstructionByUserId(long id){
        return publicationInfoDtoTransformer.makeListDto(instructionRepository.findAllByUser(userRepository.getOne(id)));
    }
    public InstructionInfo getPublicationById(long idPub){
        return publicationInfoDtoTransformer.makeDto(instructionRepository.findById(idPub));
    }

    public void editInstruction(InstructionInfo InstructionInfo){
        Instruction instruction = instructionEditDtoTransformer.makeEditModel(InstructionInfo);
        instruction.setPublishDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        instructionRepository.save(instruction);
    }
    public void deleteInstruction(long id){
        Instruction deleteInstr = instructionRepository.findById(id);
        List<Step> deleteSteps = deleteInstr.getSteps();
        deleteInstr.setSteps(null);
        deleteSteps.forEach((Step step) -> {
            step.setInstruction(null);
            stepRepository.delete(step);
        });
        deleteInstr.setCategories(null);
        //instructionRepository.save(deleteInstr);
        instructionRepository.delete(deleteInstr);

    }
    public void addComment(CommentAdd commentAddDto){
        Instruction post = this.instructionRepository.findById(commentAddDto.getId_instruction());
        List<Comment> comments = post.getComments();
        comments.add(this.commentAddTransformer.makeModel(commentAddDto));
        post.setComments(comments);
        this.instructionRepository.save(post);
    }
    public List<CommentShow> getPostComment(long idInstr){
        return commentShowTransformer.makeListDto(instructionRepository.findById(idInstr).getComments());
    }

    public void addLike(LikeEdit likeEdit){
        Like like = this.likeDtoTransformer.makeModel(likeEdit);
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
    public float setPostRating(RatingSet ratingSet){
        Instruction instr = instructionRepository.findById(ratingSet.getIdInstruction());
        Boolean newRating = true;
        for(Rating rating : instr.getRating()) {
            if(rating.getUser().getUsername().equals(ratingSet.getUsername())) {
                newRating = false;
                rating.setValue(ratingSet.getRating());
            }
        }
        if(newRating) {
            instr.getRating().add(ratingSetDtoTransformer.makeModel(ratingSet));
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
    public List<InstructionInfo> getNewsByIdUsername(String username) {
        return publicationInfoDtoTransformer.makeListDto(userRepository.findByUsername(username).getInstructions());
    }

    private void setDefaultSettings(Instruction instruction) {
        instruction.setPublishDate(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        instruction.setRating(new HashSet<>());
        instruction.setComments(new ArrayList<>());
    }
}
