package com.annotateurproject.security.controller;

import com.annotateurproject.security.Utils.JWTUtil;
import com.annotateurproject.entity.role;
import com.annotateurproject.entity.utilisateur;
import com.annotateurproject.repository.utilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class authController {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    utilisateurRepo repo;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil ;

    public authController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public utilisateur register(@RequestBody utilisateur utilisateur) {
        System.out.println(utilisateur);
        utilisateur.setPassword(passwordEncoder.encode( utilisateur.getPassword()));
        System.out.println(utilisateur.getPassword());
        utilisateur.setRole(new role("admin"));
        return repo.save(utilisateur);
    }

    @PostMapping("/login")
    public String login(@RequestBody utilisateur utilisateur) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(utilisateur.getLogin(), utilisateur.getPassword()));

           return jwtUtil.generateToken(utilisateur.getLogin());
        }catch (Exception e) {
           throw e;
        }


    }

    @GetMapping("hi")
    public String hi() {
        return "hi";
    }

}
