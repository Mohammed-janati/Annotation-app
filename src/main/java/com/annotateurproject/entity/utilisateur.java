package com.annotateurproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter @Setter
public class utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;

    private String password;
    private String login;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private role role;



}
