package sn.gainde2000.dii.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.dii.models.Importation;
import sn.gainde2000.dii.models.LoginForm;
import sn.gainde2000.dii.models.Profil;
import sn.gainde2000.dii.models.Utilisateur;
import sn.gainde2000.dii.security.JwtTokenUtil;
import sn.gainde2000.dii.services.JwtUserDetailsService;
import sn.gainde2000.dii.services.iservice.IAuthService;
import sn.gainde2000.dii.services.iservice.IImportationService;
import sn.gainde2000.dii.services.iservice.IUtilisateurService;
import sn.gainde2000.dii.utils.JwtResponse;
import sn.gainde2000.dii.utils.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {


    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final IAuthService iAuthService;
    private final JwtTokenUtil jwtTokenUtil;
    private final IUtilisateurService iUtilisateurService;
    private final IImportationService importationService;


	@GetMapping(value = "/password")
    public Response<?> encodePassord() {
        return Response.ok().setData(passwordEncoder.encode("passer"));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<?> authenticate(@RequestBody LoginForm authenticationRequest, HttpServletResponse response) throws Exception {
        try {
            @SuppressWarnings("unused")
            Authentication authenticate = authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getEmail());

            if (iAuthService.getConnectedUser().isNewConnexion()) {
                String accessToken = jwtTokenUtil.generateToken(userDetails);
                return Response.firstConnexion().setData(JwtResponse.builder()
                        .accessToken(accessToken)
                        .username(userDetails.getUsername())
                        .authorities(userDetails.getAuthorities()).build());
            }

            if (!iAuthService.getConnectedUser().getStatut()) {
                return Response.disabledAccount().setErrors("Votre compte est désactivé, veuillez contacter l'administrateur.");
            }

            String accessToken = jwtTokenUtil.generateToken(userDetails);

            return Response.ok().setData(JwtResponse.builder()
                    .accessToken(accessToken)
                    .username(userDetails.getUsername())
                    .authorities(userDetails.getAuthorities())
                    .build());
        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
            return Response.wrongCredentials().setErrors("Identifiant et/ou mot de passe incorrects. Merci de ressayer");
        }
    }

    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public Response<?> inscriptionTemporaire(@RequestPart("utilisateur") String utilisateur1) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Utilisateur utilisateur = objectMapper.readValue(utilisateur1, Utilisateur.class);
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getNumeroDii()));
            utilisateur.setDateCreation(new Date());
            utilisateur.setNewConnexion(false);
            utilisateur.setProfil(new Profil( "Demandeur", "Demandeur"));
            iUtilisateurService.save(utilisateur);
            return Response.ok().setData(utilisateur).setMessage("Utilisateur enregistré avec success");

        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
            return Response.wrongCredentials().setErrors("Identifiant et/ou mot de passe incorrects. Merci de ressayer");
        }
    }

    @RequestMapping(value = "/user/inscription", method = RequestMethod.POST)
    public Response<?> inscription(@RequestPart("utilisateur") String user, @RequestPart("numero") String numero) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Utilisateur utilisateur = objectMapper.readValue(user, Utilisateur.class);
            String numero1 = objectMapper.readValue(numero, String.class);
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            utilisateur.setDateCreation(new Date());
            utilisateur.setNewConnexion(false);

            utilisateur.setProfil(new Profil( "Demandeur", "Demandeur"));
            iUtilisateurService.save(utilisateur);
            Importation importation = importationService.findByNumero(numero1);
            importation.setUtilisateur(utilisateur);
            importationService.save(importation);

            return Response.ok().setData(utilisateur).setMessage("Utilisateur enregistré avec success");

        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
            return Response.wrongCredentials().setErrors(e.getLocalizedMessage());
        }
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
