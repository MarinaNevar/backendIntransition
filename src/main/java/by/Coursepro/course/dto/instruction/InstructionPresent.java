package by.Coursepro.course.dto.instruction;

import by.Coursepro.course.entity.Category;
import by.Coursepro.course.entity.Step;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class InstructionPresent {
    private Long id;
    private String name;
    private String description;
    private String publishDate;
    private Long id_user;
    private String userImage;
    private String authorName;
    private Float value_rating;
    private List<Step> steps;
    private Set<Category> categories;
}
