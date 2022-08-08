package sn.gainde2000.dii.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/*
- l' JwtResponse objet sera retourné par le serveur SpringBoot une fois
l' authentication réussie, il contient:
Jeton JWT
Type de schéma de jeton
Nom d' utilisateur
Tableau des autorités de l' utilisateur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
}
