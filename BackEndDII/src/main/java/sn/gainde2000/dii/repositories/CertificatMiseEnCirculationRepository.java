package sn.gainde2000.dii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.models.CertificatMiseEnCirculation;

@Repository
public interface CertificatMiseEnCirculationRepository extends JpaRepository<CertificatMiseEnCirculation,Long> {
    CertificatMiseEnCirculation findByNumeroChassis(String numeroChassis);

}
