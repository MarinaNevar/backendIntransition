package by.Coursepro.course.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "number")
    @NotNull
    @Min(1)
    private int stepNumber;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "text")
    private String stepText;
    @NotNull
    @Column(name = "publish_date")
    private String publishDate;

    @ManyToOne
    @JoinColumn(name = "id_instruction")
    private Instruction instruction;
}
