package com.annotateurproject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class annotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String chosenClass;

    @ManyToOne
    @JsonIgnore
    annotator annotator;

    @ManyToOne
@JsonIgnore
    coupleTexte coupleTexte;


}
