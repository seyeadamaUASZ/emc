package sn.gainde2000.dii.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.dii.models.Utilisateur;
import sn.gainde2000.dii.repositories.UtilisateurRepository;
import sn.gainde2000.dii.security.JwtTokenUtil;
import sn.gainde2000.dii.services.iservice.IAuthService;
import sn.gainde2000.dii.services.iservice.ISimpleEmailService;

import javax.persistence.NoResultException;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthService implements IAuthService, AuthenticationProvider {

    private final UtilisateurRepository utilisateurRepository;

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final ISimpleEmailService simpleEmailService;

    @Value("${urlApp}")
    String url;


    @Override
    public Utilisateur getConnectedUser() {
        // TODO Auto-generated method stub
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            return utilisateurRepository.findUtilisateurByEmail(email).orElse(null);
        } catch (NoResultException e) {
            // TODO: handle exception
            return null;
        }

    }

    @Override
    public Utilisateur findUtilisateurByEmail(String email) {
        return null;
    }

    @Override
    public Utilisateur findUtilisateurById(int id) {
        return utilisateurRepository.findById(id).orElse(null);
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }


}
