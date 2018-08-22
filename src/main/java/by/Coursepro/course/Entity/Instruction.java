package by.Coursepro.course.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4,max=50)
    @Column(name = "name")
    private String name;

    @Size(min = 4, max = 100)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "text")
    private String text;

    @Column(name = "image")
    private String userImage;

    @NotNull
    @Column(name = "rating_value")
    private Float ratingValue;

    @NotNull
    @Column(name = "publish_date")
    private String publishDate;

    @NotNull
    @Column(name = "author")
    private String author;

    @JsonBackReference(value="instruction-category")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "instruction_category", joinColumns = @JoinColumn(name = "id_instruction", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id"))
    public Set<Category> categories;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "instruction", cascade = CascadeType.ALL)
    @JsonBackReference(value="instruction-comments")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "instruction", cascade = CascadeType.ALL)
    private Set<Rating> rating;

    @OneToMany(mappedBy = "instruction", cascade = CascadeType.ALL)
    @JsonBackReference(value = "instruction-step")
    private Set<Step> steps;

    public Instruction(String name, String description, String text,String author, Float ratingValue) {
        this.name = name;
        this.description = description;
        this.text = text;
        this.ratingValue = ratingValue;
        this.author=author;
        this.publishDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

}
