package by.Coursepro.course.transformer.instruction;

import by.Coursepro.course.dto.instruction.InstructionInfo;
import by.Coursepro.course.entity.Instruction;
import by.Coursepro.course.repository.InstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstructionEditDtoTransformer {
    private final InstructionRepository instructionRepository;


  public Instruction makeEditModel(InstructionInfo InstructionInfo){
       Instruction instr = instructionRepository.findById(InstructionInfo.getId()).get();
        instr.setName(InstructionInfo.getName());
        instr.setDescription(InstructionInfo.getDescription());
        instr.setSteps(InstructionInfo.getSteps());
        instr.setCategories(InstructionInfo.getCategories());
        return instr;
    }
}
