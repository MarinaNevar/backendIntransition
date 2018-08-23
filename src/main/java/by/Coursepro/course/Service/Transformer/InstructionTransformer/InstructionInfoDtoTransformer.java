package by.Coursepro.course.Service.Transformer.InstructionTransformer;

import by.Coursepro.course.DTO.InstructionDTO.InstructionInfoDto;
import by.Coursepro.course.Entity.Instruction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InstructionInfoDtoTransformer {

    public InstructionInfoDto makeDto(Instruction instruction){
        InstructionInfoDto instructionInfo =new InstructionInfoDto();
        instructionInfo.setId(instruction.getId());
        instructionInfo.setDescription(instruction.getDescription());
        instructionInfo.setName(instruction.getName());
        instructionInfo.setPublishDate(instruction.getPublishDate());
        instructionInfo.setAuthorName(instruction.getPublishDate());
        instructionInfo.setValue_rating(instruction.getRatingValue());
        instructionInfo.setId_user(instruction.getUser().getId());
        instructionInfo.setUserImage(instruction.getUserImage());

        return instructionInfo;
    }


}
