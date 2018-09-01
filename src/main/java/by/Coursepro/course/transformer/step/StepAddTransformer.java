package by.Coursepro.course.transformer.step;

import by.Coursepro.course.dto.step.StepAdd;
import by.Coursepro.course.entity.Step;
import by.Coursepro.course.repository.InstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StepAddTransformer {

    private final InstructionRepository instructionRepository;

    public Step makeModel(StepAdd stepAdd){
        Step step = new Step();
        step.setName(stepAdd.getName());
        step.setStepNumber(stepAdd.getNumber());
        step.setText(stepAdd.getText());
        step.setInstruction(this.instructionRepository.findById(stepAdd.getId_instruction()));
        return step;
    }
}
