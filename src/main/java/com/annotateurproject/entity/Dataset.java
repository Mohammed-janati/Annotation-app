package com.annotateurproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String description;

    @OneToMany(mappedBy = "dataset",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    List<tache> taches;

    @OneToMany(mappedBy = "dataset",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
            @JsonIgnore
    List<coupleTexte> coupleTexte;

    @OneToMany(mappedBy = "dataset",cascade = CascadeType.PERSIST)
    List<possibleClass> classes;


}
