package sn.gainde2000.dii.services.iservice;


import sn.gainde2000.dii.models.Utilisateur;

public interface IAuthService {
    Utilisateur getConnectedUser();
    Utilisateur findUtilisateurByEmail(String email);
    Utilisateur findUtilisateurById(int id);
}
