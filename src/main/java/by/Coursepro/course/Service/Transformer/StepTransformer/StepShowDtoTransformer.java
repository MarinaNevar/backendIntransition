package by.Coursepro.course.Service.Transformer.StepTransformer;

import by.Coursepro.course.DTO.CommentDTO.CommentShowDto;
import by.Coursepro.course.DTO.InstructionDTO.PublicInfoDto;
import by.Coursepro.course.DTO.StepDTO.StepShowDto;
import by.Coursepro.course.Entity.Instruction;
import by.Coursepro.course.Entity.Step;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StepShowDtoTransformer {

    public StepShowDto makeDto(Step step){
        StepShowDto showDto = new StepShowDto();
        showDto.setName(step.getName());
        showDto.setText(step.getStepText());
        showDto.setNumber(step.getStepNumber());
        showDto.setPublish_date(step.getPublishDate());
        return showDto;
    }

    public List<StepShowDto> makeListDto(List<Step> stepList){
        List<StepShowDto> stepShowDto=new ArrayList<>();
        for(Step steps: stepList){
            stepShowDto.add(makeDto(steps));
        }
        return stepShowDto;
    }

}
