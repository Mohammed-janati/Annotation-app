package com.annotateurproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class annotator extends utilisateur{

    @OneToMany(mappedBy="annotator",fetch = FetchType.LAZY)
    List<tache> taches;

    @OneToMany(mappedBy = "annotator",fetch = FetchType.LAZY)
    List<annotation> annotations;
}
