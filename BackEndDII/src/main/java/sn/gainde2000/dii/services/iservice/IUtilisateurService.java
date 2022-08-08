package sn.gainde2000.dii.services.iservice;

import sn.gainde2000.dii.models.Utilisateur;

import java.util.List;


public interface IUtilisateurService {
    Utilisateur save(Utilisateur utilisateur);
    Utilisateur findByEmail(String email);



    List<Utilisateur> listeUtilisateurs();
}
