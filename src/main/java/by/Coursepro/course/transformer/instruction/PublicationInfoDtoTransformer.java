package by.Coursepro.course.transformer.instruction;

import by.Coursepro.course.dto.instruction.InstructionInfo;
import by.Coursepro.course.dto.instruction.InstructionShowInfo;
import by.Coursepro.course.entity.Instruction;
import by.Coursepro.course.entity.User;
import by.Coursepro.course.repository.UserRepository;
import by.Coursepro.course.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PublicationInfoDtoTransformer {

    private final UserRepository userRepository;
    private final CategoriesService categoriesService;

    public Instruction makeModel(InstructionInfo InstructionInfo){
        Instruction instruction=new Instruction();
        instruction.setId(InstructionInfo.getId());
        instruction.setName(InstructionInfo.getName());
        instruction.setDescription(InstructionInfo.getDescription());
        instruction.setCategories(this.categoriesService.getCategories(InstructionInfo.getCategories()));
        instruction.setSteps(InstructionInfo.getSteps());
        User user = userRepository.findById(InstructionInfo.getId_user());
        //instruction.setUserImage(user.getAvatar());
        instruction .setUser(user);
        instruction.setRatingValue(InstructionInfo.getValue_rating());
        instruction.setSteps(InstructionInfo.getSteps());
        return instruction;
    }

    public InstructionShowInfo makeShowDto(Instruction instruction){
        InstructionShowInfo showDto = new InstructionShowInfo();
        showDto.setId(instruction.getId());
        showDto.setName(instruction.getName());
        showDto.setDescription(instruction.getDescription());
        showDto.setPublishDate(instruction.getPublishDate());
        showDto.setValue_rating(instruction.getRatingValue());
        return showDto;
    }


    public InstructionInfo makeDto(Instruction instruction){
        InstructionInfo InstructionInfo = new InstructionInfo();
        InstructionInfo.setId_user(instruction.getUser().getId());
        InstructionInfo.setId(instruction.getId());
       // InstructionInfo.setUserImage(instruction.getUserImage());
        InstructionInfo.setValue_rating(instruction.getRatingValue());
      //  InstructionInfo.setPublishDate(instruction.getPublishDate());
        InstructionInfo.setDescription(instruction.getDescription());
        InstructionInfo.setName(instruction.getName());
        InstructionInfo.setSteps(instruction.getSteps());
        InstructionInfo.setCategories(instruction.getCategories());
        return InstructionInfo;
    }

    public List<InstructionShowInfo> makeShowList(List<Instruction> instructionList){
        List<InstructionShowInfo> instrShow=new ArrayList<>();
        for(Instruction instruction: instructionList){
            instrShow.add(makeShowDto(instruction));
        }
        return instrShow;
    }

    public List<InstructionInfo> makeListDto(List<Instruction> instructionList){
            List<InstructionInfo> instructionInfoList =new ArrayList<>();
            for(Instruction instruction: instructionList){
                instructionInfoList.add(makeDto(instruction));
            }
            return instructionInfoList;
    }
}
