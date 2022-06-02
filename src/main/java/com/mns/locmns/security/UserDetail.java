package com.mns.locmns.security;

import com.mns.locmns.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetail implements UserDetails{
    @Autowired
    private Utilisateur utilisateur;
    public UserDetail(Utilisateur utilisateur){this.utilisateur=utilisateur;}
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        ArrayList<SimpleGrantedAuthority> listeAuthority = new ArrayList<>();

        if(this.utilisateur.isAdmin()) {
            listeAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            listeAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return listeAuthority;
    }

    @Override
    public String getPassword() {
        return utilisateur.getPassword();
    }

    @Override
    public String getUsername() {
        return utilisateur.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
