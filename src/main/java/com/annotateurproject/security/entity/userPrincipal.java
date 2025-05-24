package com.annotateurproject.security.entity;

import com.annotateurproject.entity.utilisateur;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@ToString
@Getter @Setter
public class userPrincipal implements UserDetails {
    utilisateur user;

    public userPrincipal(utilisateur user) {
this.user = user;
    }
@Override
public boolean isAccountNonLocked(){
if(this.user.isNotActive())
    return true;
else return false;
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
