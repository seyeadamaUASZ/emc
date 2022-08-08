package sn.gainde2000.dii.paiement.service;


import sn.gainde2000.dii.paiement.entity.MoyenPaiement;

import java.util.List;
import java.util.Optional;

public interface ImoyenPaimentService {
    List<MoyenPaiement> getListMoyen();

    Optional<MoyenPaiement> findByMoyenId(Long id);

    MoyenPaiement save(MoyenPaiement m);

    void delete(MoyenPaiement m);
}
