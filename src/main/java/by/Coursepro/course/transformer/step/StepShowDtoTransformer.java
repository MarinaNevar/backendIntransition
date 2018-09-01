package by.Coursepro.course.transformer.step;

import by.Coursepro.course.dto.step.StepShow;
import by.Coursepro.course.entity.Step;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StepShowDtoTransformer {

    public StepShow makeDto(Step step){
        StepShow showDto = new StepShow();
        showDto.setName(step.getName());
        showDto.setText(step.getText());
        showDto.setNumber(step.getStepNumber());
        return showDto;
    }

    public List<StepShow> makeListDto(List<Step> stepList){
        List<StepShow> stepShow =new ArrayList<>();
        for(Step steps: stepList){
            stepShow.add(makeDto(steps));
        }
        return stepShow;
    }

}
