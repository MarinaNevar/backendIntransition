package by.Coursepro.course.transformer.instruction;

import by.Coursepro.course.dto.instruction.PostInfo;
import by.Coursepro.course.entity.Instruction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstructionInfoDtoTransformer {

    public PostInfo makeDto(Instruction instruction){
        PostInfo instructionInfo =new PostInfo();
        instructionInfo.setId(instruction.getId());
        instructionInfo.setDescription(instruction.getDescription());
        instructionInfo.setName(instruction.getName());
        instructionInfo.setPublishDate(instruction.getPublishDate());
        instructionInfo.setValue_rating(instruction.getRatingValue());
        instructionInfo.setId_user(instruction.getUser().getId());
        instructionInfo.setUserImage(instruction.getUserImage());
        return instructionInfo;
    }


}
