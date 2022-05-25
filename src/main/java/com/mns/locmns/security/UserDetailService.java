package com.mns.locmns.security;

import com.mns.locmns.dao.UtilisateurDao;
import com.mns.locmns.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private UtilisateurDao utilisateurDao;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserDetailService(UtilisateurDao utilisateurDao){this.utilisateurDao=utilisateurDao;}

    @Override
    public UserDetail loadUserByUsername(String login) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurDao
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Mauvais pseudo / mot de passe"));

        UserDetail userDetails = new UserDetail(utilisateur);

        return userDetails;
    }
}
