package com.annotateurproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class coupleTexte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String T1;
    String T2;

    @ManyToOne
    @JoinColumn(nullable = true)
    tache tache;

    @OneToMany(mappedBy = "coupleTexte")

    List<annotation> annotation;

    @ManyToOne

    Dataset dataset;
}
