package by.Coursepro.course.Service.Transformer.StepTransformer;

import by.Coursepro.course.DTO.StepDTO.StepAddDto;
import by.Coursepro.course.Entity.Step;
import by.Coursepro.course.Repository.InstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StepAddTransformer {

    private final InstructionRepository instructionRepository;

    public Step makeModel(StepAddDto stepAddDto){
        Step step = new Step();
        step.setName(stepAddDto.getName());
        step.setStepNumber(stepAddDto.getNumber());
        step.setStepText(stepAddDto.getText());
        step.setInstruction(this.instructionRepository.findById(stepAddDto.getId_instruction()));
        return step;
    }
}
