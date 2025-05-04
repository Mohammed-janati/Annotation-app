package com.annotateurproject.security.entity;

import com.annotateurproject.entity.utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class userPrincipal implements UserDetails {
    utilisateur user;

    public userPrincipal(utilisateur user) {
this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().getRole()));
    }

    @Override
    public String getPassword() {
return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getLogin();
    }
}
