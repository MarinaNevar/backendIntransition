package by.Coursepro.course.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=3, max=18)
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "avatar")
    private String avatar;

    @Column(name ="isActive")
    private Boolean isActive;

    @Column(name = "amount_like")
    private long amountLike;

    @Column(name = "isBlocked")
    private boolean isBlocked;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference(value = "instruction-user")
    private List<Instruction> instructions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference(value="user-comment")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference(value="user-rating")
    private Set<Rating> ratings;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference(value="user-like")
    private Set<Like> likes;

    @ManyToOne
    @JoinColumn(name = "id_language")
    private Language language;


    @ManyToOne
    @JoinColumn(name = "id_theme")
    private Theme theme;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }




}