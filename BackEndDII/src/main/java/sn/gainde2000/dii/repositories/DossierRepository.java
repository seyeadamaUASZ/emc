package sn.gainde2000.dii.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.models.Dossier;

import java.util.List;

@Repository
public interface DossierRepository extends JpaRepository<Dossier,String> {
    Dossier findByNumeroChassis(String numeroChassis);
    Dossier findByNumeroAndPayeTrue(String numero);

    Dossier findByNumero(String numero);

}
