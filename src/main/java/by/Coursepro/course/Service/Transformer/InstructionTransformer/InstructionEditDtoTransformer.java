package by.Coursepro.course.Service.Transformer.InstructionTransformer;

import by.Coursepro.course.DTO.InstructionDTO.PublicInfoDto;
import by.Coursepro.course.Entity.Instruction;
import by.Coursepro.course.Repository.InstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstructionEditDtoTransformer {
    private final InstructionRepository instructionRepository;


    public Instruction makeEditModel(PublicInfoDto publicInfoDto){
        Instruction instr = instructionRepository.findById((long) publicInfoDto.getInstructionInfoDto().getId());
        instr.setName(publicInfoDto.getInstructionInfoDto().getName());
        instr.setDescription(publicInfoDto.getInstructionInfoDto().getDescription());
        instr.setCategories(publicInfoDto.getCategories());
        instr.setText(publicInfoDto.getInstructionInfoDto().getText());
        return instr;
    }
}
