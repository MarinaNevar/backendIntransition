package by.Coursepro.course.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "instructions")
@Getter
@Setter
@NoArgsConstructor
@Indexed
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Size(min = 4,max=50)
    @Column(name = "name")
    private String name;

    @Size(min = 4, max = 100)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "description")
    private String description;

//    @NotNull
//    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
//    @Column(name = "text")
//    private String text;

    @Column(name = "image")
    private String userImage;

    @NotNull
    @Column(name = "rating_value")
    private Float ratingValue;

    @NotNull
    @Column(name = "publish_date")
    @Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
    @DateBridge(resolution=Resolution.DAY)
    private String publishDate;

    @JsonBackReference(value="instruction-category")
    @IndexedEmbedded
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "instruction_category", joinColumns = @JoinColumn(name = "id_instruction", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id"))
    public Set<Category> categories;

    @ManyToOne
    @IndexedEmbedded
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "instruction", cascade = CascadeType.ALL)
    @JsonBackReference(value="instruction-comments")
    private List<Comment> comments;

    @OneToMany(mappedBy = "instruction", cascade = CascadeType.ALL)
    private Set<Rating> rating;

    @OneToMany(mappedBy = "instruction", cascade = CascadeType.ALL)
    @JsonBackReference(value = "instruction-step")
    private List<Step> steps;

    public Instruction(String name, String description, Float ratingValue) {
        this.name = name;
        this.description = description;
        this.ratingValue = ratingValue;
        this.publishDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

}
