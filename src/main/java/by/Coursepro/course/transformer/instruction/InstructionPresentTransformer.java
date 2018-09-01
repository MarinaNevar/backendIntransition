package by.Coursepro.course.transformer.instruction;

import by.Coursepro.course.dto.instruction.InstructionPresent;
import by.Coursepro.course.entity.Instruction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InstructionPresentTransformer {

    public InstructionPresent makeDto(Instruction instruction) {
        InstructionPresent instructionPresent = new InstructionPresent();
        instructionPresent.setId(instruction.getId());
        instructionPresent.setAuthorName(instruction.getUser().getUsername());
        instructionPresent.setCategories(instruction.getCategories());
        instructionPresent.setDescription(instruction.getDescription());
        instructionPresent.setId_user(instruction.getUser().getId());
        instructionPresent.setName(instruction.getName());
        instructionPresent.setPublishDate(instruction.getPublishDate());
        instructionPresent.setSteps(instruction.getSteps());
        instructionPresent.setUserImage(instruction.getUserImage());
        instructionPresent.setValue_rating(instruction.getRatingValue());
        return instructionPresent;
    }

    public List<InstructionPresent> makeListDto(List<Instruction> instructions) {
        List<InstructionPresent> instructionPresents=new ArrayList<>();
        for(Instruction instruction: instructions){
            instructionPresents.add(makeDto(instruction));
        }
        return instructionPresents;
    }
}
