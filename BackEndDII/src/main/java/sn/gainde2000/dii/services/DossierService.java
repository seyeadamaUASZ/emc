package sn.gainde2000.dii.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sn.gainde2000.dii.config.DemandeIdGenerator;
import sn.gainde2000.dii.models.Dossier;
import sn.gainde2000.dii.repositories.DossierRepository;
import sn.gainde2000.dii.services.iservice.IDossier;
import sn.gainde2000.dii.utils.Response;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DossierService implements IDossier {
    private final DossierRepository dossierRepository;

    @Override
    public Dossier findByNumeroChassis(String numeroChassis) {
       Dossier dossier = dossierRepository.findByNumeroChassis(numeroChassis);
       if(Objects.nonNull(dossier)){
           return dossier;
       }
       return null;
    }

    @Override
    public Dossier findByNumeroAndPayeTrue(String numero) {
        Dossier dossier = dossierRepository.findByNumeroAndPayeTrue(numero);
        if(Objects.nonNull(dossier)){
            return dossier;
        }
        return null;
    }

    @Override
    public Dossier findByNumero(String numero) {
        Dossier dossier = dossierRepository.findByNumero(numero);
        if(Objects.nonNull(dossier)){
            return dossier;
        }
        return null;    }

    @Override
    public Page<Dossier> findAll(int page, int size) {
        Page<Dossier> pageDossiers = dossierRepository.findAll(PageRequest.of(page,size));
        return pageDossiers;
    }

    @Override
    public Dossier createDossier(Dossier dossier) {
        dossier.setNumero(DemandeIdGenerator);
        return dossierRepository.save(dossier);
    }
}
