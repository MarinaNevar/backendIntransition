package by.Coursepro.course.Service.Transformer.InstructionTransformer;

import by.Coursepro.course.DTO.InstructionDTO.PublicInfoDto;
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

    public Instruction makeModel(PublicInfoDto publicInfoDto){
        Instruction instruction=new Instruction();
        instruction.setId(publicInfoDto.getInstructionInfoDto().getId());
        instruction.setName(publicInfoDto.getInstructionInfoDto().getName());
        instruction.setDescription(publicInfoDto.getInstructionInfoDto().getDescription());
        User user = this.userRepository.findById(publicInfoDto.getInstructionInfoDto().getId_user());
        instruction.setUserImage(user.getAvatar());
        instruction.setUser(user);
        instruction.setRatingValue(publicInfoDto.getInstructionInfoDto().getValue_rating());
        instruction.setSteps(publicInfoDto.getSteps());
        return instruction;
    }

    public PublicInfoDto makeDto(Instruction instruction){
        PublicInfoDto publicInfoDto = new PublicInfoDto();
        publicInfoDto.setInstructionInfoDto(instructionInfoDtoTransformer.makeDto(instruction));
        publicInfoDto.setCategories(instruction.getCategories());
        return publicInfoDto;
    }
    public Set<PublicInfoDto> makeSetDto(Set<Instruction> instructionSet) {
        Set<PublicInfoDto> newsInfoDtoSet = new HashSet<>();
        for(Instruction instr : instructionSet) {
            newsInfoDtoSet.add(makeDto(instr));
        }
        return newsInfoDtoSet;
    }

    public List<PublicInfoDto> makeListDto(List<Instruction> instructionList){
            List<PublicInfoDto> instructionInfoDtoList=new ArrayList<>();
            for(Instruction instruction: instructionList){
                instructionInfoDtoList.add(makeDto(instruction));
            }
            return instructionInfoDtoList;
    }
}
