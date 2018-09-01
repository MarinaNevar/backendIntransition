package by.Coursepro.course.dto.instruction;

import by.Coursepro.course.entity.Category;
import by.Coursepro.course.entity.Step;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
public class InstructionInfo {
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
