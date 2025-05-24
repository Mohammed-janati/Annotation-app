package com.annotateurproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class tache {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDateTime date;
    @ManyToOne
@JsonIgnore
    annotator annotator;

    @OneToMany(mappedBy = "tache",cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<coupleTexte> coupletexte;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //don't serialize
    Dataset dataset;
}
