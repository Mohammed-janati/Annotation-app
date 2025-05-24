package com.annotateurproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter  @Setter
@AllArgsConstructor @NoArgsConstructor
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<utilisateur> utilisateur;

    public role(String annotateur) {
        this.role = annotateur;
    }
}
