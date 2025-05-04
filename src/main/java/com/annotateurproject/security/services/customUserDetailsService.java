package com.annotateurproject.security.services;

import com.annotateurproject.security.entity.userPrincipal;
import com.annotateurproject.entity.utilisateur;
import com.annotateurproject.repository.utilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customUserDetailsService implements UserDetailsService {

    @Autowired
    utilisateurRepo repo;

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    utilisateur user=repo.findByLogin(username);
    return new userPrincipal(user);
    }
}
