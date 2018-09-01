package by.Coursepro.course.transformer.step;

import by.Coursepro.course.dto.step.StepEdit;
import by.Coursepro.course.entity.Step;
import by.Coursepro.course.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StepEditDtoTransformer {

    private final StepRepository stepRepository;

    public Step makeEditModel(StepEdit stepEdit){
        Step step = stepRepository.findById((long) stepEdit.getId());
        step.setName(stepEdit.getName());
        step.setText(stepEdit.getText());
        return step;
    }
}
