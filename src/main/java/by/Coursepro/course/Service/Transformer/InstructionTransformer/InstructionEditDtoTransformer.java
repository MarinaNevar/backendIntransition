package by.Coursepro.course.Service.Transformer.InstructionTransformer;

import by.Coursepro.course.DTO.InstructionDTO.InstructionInfoDto;
import by.Coursepro.course.Entity.Instruction;
import by.Coursepro.course.Repository.InstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstructionEditDtoTransformer {
    private final InstructionRepository instructionRepository;


  public Instruction makeEditModel(InstructionInfoDto instructionInfoDto){
       Instruction instr = instructionRepository.findById(instructionInfoDto.getId()).get();
        instr.setName(instructionInfoDto.getName());
        instr.setDescription(instructionInfoDto.getDescription());
        instr.setSteps(instructionInfoDto.getSteps());
        instr.setCategories(instructionInfoDto.getCategories());
        return instr;
    }
}
