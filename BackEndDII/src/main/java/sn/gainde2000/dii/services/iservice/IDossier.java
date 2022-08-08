package sn.gainde2000.dii.services.iservice;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sn.gainde2000.dii.models.Dossier;
import sn.gainde2000.dii.utils.Response;

import java.util.List;

@Service
public interface IDossier {

    Dossier findByNumeroChassis(String numeroChassis);
    Dossier findByNumeroAndPayeTrue(String numero);

    Dossier findByNumero(String numero);

    Page<Dossier> findAll(int page, int size);

    Dossier createDossier(Dossier dossier);
}
