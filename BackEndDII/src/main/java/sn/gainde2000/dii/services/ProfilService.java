package sn.gainde2000.dii.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.dii.models.Profil;
import sn.gainde2000.dii.repositories.ProfilRepository;
import sn.gainde2000.dii.services.iservice.IProfilService;

@RequiredArgsConstructor
@Service
public class ProfilService implements IProfilService {

    @Autowired
    private ProfilRepository profilRepository;

    @Override
    public Profil save(Profil profil) {
        return profilRepository.save(profil);
    }

}
