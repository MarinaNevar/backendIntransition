package by.Coursepro.course.Controller;

import by.Coursepro.course.DTO.CommentDTO.CommentAddDto;
import by.Coursepro.course.DTO.CommentDTO.CommentShowDto;
import by.Coursepro.course.DTO.InstructionDTO.InstructionInfoDto;
import by.Coursepro.course.DTO.LikeDTO.LikeDto;
import by.Coursepro.course.DTO.RatingDTO.RatingSetDto;
import by.Coursepro.course.DTO.StepDTO.StepAddDto;
import by.Coursepro.course.DTO.StepDTO.StepEditDto;
import by.Coursepro.course.DTO.StepDTO.StepShowDto;
import by.Coursepro.course.Entity.Category;
import by.Coursepro.course.Service.CategoriesService;
import by.Coursepro.course.Service.CloudStorageService;
import by.Coursepro.course.Service.InstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = "/instructions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ManualController {
    private final InstructionService instructionService;
    private final CloudStorageService cloudStorageService;
    private final CategoriesService categoriesService;

    @GetMapping("/getAll")
    public List<InstructionInfoDto> getInstructions() {
        return this.instructionService.getInstructions();
    }

    @PostMapping("/addInstr")
    public void addInstr(@RequestBody InstructionInfoDto instr) {
        this.instructionService.addInstruction(instr);
    }
    @PostMapping("/addImageToPost")
    public String addImageToPost(@RequestParam("file") MultipartFile image) {
        return this.cloudStorageService.uploadImage(image);
    }

    @GetMapping("/getedit/{id}")
    public void getedit(@RequestBody long id){
        this.instructionService.getInstructionByUserId(id);
    }

    @PostMapping("/edit")
       public void editInstr(@RequestBody InstructionInfoDto publicInfoDto){
       this.instructionService.editInstruction(publicInfoDto);
    }
    @DeleteMapping("/deleteInstr/{idInstr}")
    public void deleteInstr(@RequestBody Long idInstr){
        this.instructionService.deleteInstruction(idInstr);
    }

    @PostMapping("/addComment")
    public void addComment(@RequestBody CommentAddDto commentAddDto){
        this.instructionService.addComment(commentAddDto);
    }
    @GetMapping("/comments/{idInstr}")
    public Set<CommentShowDto> getInstrComments(@PathVariable long idInstr){
        return this.instructionService.getPostComment(idInstr);
    }
    @PostMapping("/addLike")
    private void addLike(@RequestBody LikeDto likeDto){
        this.instructionService.addLike(likeDto);
    }

    @PostMapping("/setRating")
    public float setRating(@RequestBody RatingSetDto ratingSetDto){
        return this.instructionService.setPostRating(ratingSetDto);
    }

    @GetMapping("/getRating/{idInst}")
    public float getRating(@PathVariable long idInst){
        return this.instructionService.getPostRating(idInst);
    }

    @PostMapping("/addStep")
    public void addStep(@RequestBody StepAddDto stepAddDto){
        this.instructionService.addStep(stepAddDto);
    }
    @GetMapping("/getSteps/{idInstr}")
    public List<StepShowDto> getSteps(@PathVariable Long idInstr){
        return this.instructionService.getSteps(idInstr);
    }
    @PostMapping("/editStep")
    public void editStep(@RequestBody StepEditDto stepEditDto){
        this.instructionService.editStep(stepEditDto);
    }

    @GetMapping("/getAllCategories")
    public Set<Category> getAllCategories(Set<Category> categories) {
        return this.categoriesService.getCategories(categories);
    }


}
