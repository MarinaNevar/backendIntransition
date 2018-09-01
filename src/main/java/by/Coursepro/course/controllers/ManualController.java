package by.Coursepro.course.controllers;

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
import by.Coursepro.course.entity.Category;
import by.Coursepro.course.service.CategoriesService;
import by.Coursepro.course.service.CloudStorageService;
import by.Coursepro.course.service.InstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/instructions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ManualController {
    private final InstructionService instructionService;
    private final CloudStorageService cloudStorageService;
    private final CategoriesService categoriesService;

    @GetMapping
    List<InstructionPresent> getInstructions() {
        return this.instructionService.getInstructions();
    }

    @PostMapping
    void addInstr(@RequestBody InstructionInfo instr) {
        this.instructionService.addInstruction(instr);
    }

    @PostMapping("/addImageToPost")
    public String addImageToPost(@RequestParam("file") MultipartFile image) {
        return this.cloudStorageService.uploadImage(image);
    }

    @GetMapping("/users/{id}")
    List<InstructionInfo> getInstructionsByUserId(@PathVariable long id){
       return this.instructionService.getInstructionByUserId(id);
    }

    @GetMapping("/{id}")
    InstructionInfo getInstruction(@PathVariable long id){
        return this.instructionService.getPublicationById(id);
    }

    @PutMapping
    void editInstr(@RequestBody InstructionInfo publicInfoDto){
       this.instructionService.editInstruction(publicInfoDto);
    }

    @DeleteMapping("/{idInstr}")
    void deleteInstr(@PathVariable long idInstr){
        this.instructionService.deleteInstruction(idInstr);
    }

    @PostMapping("/{instructionId}/comments")
    void addComment(@RequestBody CommentAdd commentAddDto){
        this.instructionService.addComment(commentAddDto);
    }

    @GetMapping("/{instructionId}/comments")
    List<CommentShow> getInstrComments(@PathVariable long instructionId){
        return this.instructionService.getPostComment(instructionId);
    }
    @PostMapping("/comments/{commentId}/likes")
    void addLike(@RequestBody LikeEdit likeEdit){
        this.instructionService.addLike(likeEdit);
    }

    @PostMapping("/{instructionId}/ratings")
    float setRating(@RequestBody RatingSet ratingSet){
        return this.instructionService.setPostRating(ratingSet);
    }

    @GetMapping("{instructionId}/ratings")
    float getRating(@PathVariable long instructionId){
        return this.instructionService.getPostRating(instructionId);
    }

    @GetMapping("/user/{username}")
    List<InstructionShowInfo> getInstructionByUsername(@PathVariable String username){
        return this.instructionService.getInstructionByUsername(username);
    }

    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories() {
        return this.categoriesService.getAllCategories();
    }


}
