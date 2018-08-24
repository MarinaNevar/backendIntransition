package by.Coursepro.course.Service.Transformer.InstructionTransformer;

import by.Coursepro.course.DTO.InstructionDTO.PostInfoDto;
import by.Coursepro.course.Entity.Instruction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstructionInfoDtoTransformer {

    public PostInfoDto makeDto(Instruction instruction){
        PostInfoDto instructionInfo =new PostInfoDto();
        instructionInfo.setId(instruction.getId());
        instructionInfo.setDescription(instruction.getDescription());
        instructionInfo.setName(instruction.getName());
        instructionInfo.setPublishDate(instruction.getPublishDate());
        instructionInfo.setAuthorName(instruction.getAuthor());
        instructionInfo.setValue_rating(instruction.getRatingValue());
        instructionInfo.setId_user(instruction.getUser().getId());
        instructionInfo.setUserImage(instruction.getUserImage());

        return instructionInfo;
    }


}
