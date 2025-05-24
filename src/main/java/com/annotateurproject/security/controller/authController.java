package com.annotateurproject.security.controller;

import com.annotateurproject.entity.annotator;
import com.annotateurproject.repository.annotatorRepo;
import com.annotateurproject.repository.roleRepo;
import com.annotateurproject.security.Utils.JWTUtil;
import com.annotateurproject.entity.role;
import com.annotateurproject.entity.utilisateur;
import com.annotateurproject.repository.utilisateurRepo;
import com.annotateurproject.security.Utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    annotatorRepo arepo;
    @Autowired
    AuthenticationManager authenticationManager;
@Autowired
    roleRepo roleRepo;
    @Autowired
    JWTUtil jwtUtil ;
    @Autowired
    private ApplicationArguments springApplicationArguments;

    public authController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody annotator utilisateur) {
        System.out.println(utilisateur);
        PasswordGenerator a=new PasswordGenerator(12, true, true, true);
        String password=a.generate();
        utilisateur.setPassword(password);
        utilisateur.setPassword(passwordEncoder.encode( utilisateur.getPassword()));
        System.out.println(utilisateur.getPassword());
        role r=roleRepo.findById(1).orElseThrow(()->new RuntimeException("Role Not Found"));
        utilisateur.setRole(r);

        arepo.save(utilisateur) ;
        return password;
    }

    @PostMapping("/login")
    public String login(@RequestBody utilisateur utilisateur) throws NoSuchFieldException {
        try{
           var x= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(utilisateur.getLogin(), utilisateur.getPassword()));
            utilisateur u= repo.findByLogin(x.getName());
           return jwtUtil.generateToken(x.getName(),u.getRole().getRole(),u.getFirstName(),u.getLastName(),u.getEmail(),u.getId());
        }catch (Exception e) {
           throw e;
        }


    }



}
