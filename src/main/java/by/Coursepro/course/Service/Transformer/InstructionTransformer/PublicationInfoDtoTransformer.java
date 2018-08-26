package by.Coursepro.course.Service.Transformer.InstructionTransformer;

import by.Coursepro.course.DTO.InstructionDTO.InstructionInfoDto;
import by.Coursepro.course.Entity.Instruction;
import by.Coursepro.course.Entity.User;
import by.Coursepro.course.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PublicationInfoDtoTransformer {

    private final UserRepository userRepository;
    private final InstructionInfoDtoTransformer instructionInfoDtoTransformer;

    public Instruction makeModel(InstructionInfoDto instructionInfoDto){
        Instruction instruction=new Instruction();
        instruction.setId(instructionInfoDto.getId());
        instruction.setName(instructionInfoDto.getName());
        instruction.setDescription(instructionInfoDto.getDescription());
        User user = this.userRepository.findById(instructionInfoDto.getId_user());
        instruction.setUserImage(user.getAvatar());
        instruction .setUser(user);
        instruction.setRatingValue(instructionInfoDto.getValue_rating());
        instruction.setSteps(instructionInfoDto.getSteps());
        return instruction;
    }

    public InstructionInfoDto makeDto(Instruction instruction){
        InstructionInfoDto instructionInfoDto = new InstructionInfoDto();
       // instructionInfoDto.setId_user(instruction.getUser().getId());
       // instructionInfoDto.setId(instruction.getId());
       // instructionInfoDto.setUserImage(instruction.getUserImage());
        instructionInfoDto.setValue_rating(instruction.getRatingValue());
        instructionInfoDto.setAuthorName(instruction.getAuthor());
      //  instructionInfoDto.setPublishDate(instruction.getPublishDate());
        instructionInfoDto.setDescription(instruction.getDescription());
        instructionInfoDto.setName(instruction.getName());
        instructionInfoDto.setCategories(instruction.getCategories());
        return instructionInfoDto;
    }
    public Set<InstructionInfoDto> makeSetDto(Set<Instruction> instructionSet) {
        Set<InstructionInfoDto> newsInfoDtoSet = new HashSet<>();
        for(Instruction instr : instructionSet) {
            newsInfoDtoSet.add(makeDto(instr));
        }
        return newsInfoDtoSet;
    }

    public List<InstructionInfoDto> makeListDto(List<Instruction> instructionList){
            List<InstructionInfoDto> instructionInfoDtoList=new ArrayList<>();
            for(Instruction instruction: instructionList){
                instructionInfoDtoList.add(makeDto(instruction));
            }
            return instructionInfoDtoList;
    }
}
