package by.Coursepro.course.Service.Transformer.StepTransformer;

import by.Coursepro.course.DTO.StepDTO.StepEditDto;
import by.Coursepro.course.Entity.Step;
import by.Coursepro.course.Repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StepEditDtoTransformer {

    private final StepRepository stepRepository;

    public Step makeEditModel(StepEditDto stepEditDto){
        Step step = stepRepository.findById((long) stepEditDto.getId());
        step.setName(stepEditDto.getName());
        step.setStepText(stepEditDto.getText());
        return step;
    }
}
