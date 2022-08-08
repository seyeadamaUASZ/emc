/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.dii.paiement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.paiement.entity.Paiement;

import java.util.List;

/**
 * @author yougadieng
 */
@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    Paiement findByPaiReferencePaiement(String referencePaiement);

    List<Paiement> findBypaiReferenceClient(String username);

    Paiement findByPaiIdFacture(Long idFacture);

    /*@Transactional
    @Modifying
    @Query("update Paiement set montant=solde-montant WHERE p.montant =:rapport AND mr.menu =:menu")
    public void payerr(@Param("rapport") Long rapport, @Param("menu") String menu);*/
}
