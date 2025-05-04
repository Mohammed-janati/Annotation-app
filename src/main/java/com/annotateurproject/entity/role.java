package com.annotateurproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter  @Setter
@AllArgsConstructor @NoArgsConstructor
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;


    public role(String annotateur) {
        this.role = annotateur;
    }
}
