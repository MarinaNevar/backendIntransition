package by.Coursepro.course.DTO.InstructionDTO;

import by.Coursepro.course.Entity.Category;
import by.Coursepro.course.Entity.Step;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class PublicInfoDto {

    private InstructionInfoDto instructionInfoDto;
    private Set<Category> categories;
    private Set<Step> steps;
}
