package sn.gainde2000.dii.paiement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.paiement.entity.MoyenPaiement;

@Repository
public interface MoyenPaiementRepository extends JpaRepository<MoyenPaiement,Long> {

}
