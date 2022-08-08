package sn.gainde2000.dii.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.dii.models.Utilisateur;
import sn.gainde2000.dii.repositories.UtilisateurRepository;
import sn.gainde2000.dii.services.iservice.IUtilisateurService;

@RequiredArgsConstructor
@Service
public class UtilisateurService implements IUtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public List<Utilisateur> listeUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}
