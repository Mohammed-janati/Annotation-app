package com.annotateurproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    private String email;

    private String password;
    @Column(unique = true)
    private String login;

private  boolean notActive=true;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private role role;



}
