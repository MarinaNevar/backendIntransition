package by.Coursepro.course.DTO.InstructionDTO;

import by.Coursepro.course.Entity.Category;
import by.Coursepro.course.Entity.Step;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
public class InstructionInfoDto {
    private Long id;
    private String name;
    private String description;
   // private String publishDate;
    //private String userImage;
    private long id_user;
    private float value_rating;
    private String authorName;
    private Set<Category> categories;
    private List<Step> steps;
}
