package sn.gainde2000.dii.paiement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.dii.paiement.entity.MoyenPaiement;
import sn.gainde2000.dii.paiement.repository.MoyenPaiementRepository;
import sn.gainde2000.dii.paiement.service.ImoyenPaimentService;

import java.util.List;
import java.util.Optional;

@Service
public class MoyenPaiementServiceImpl implements ImoyenPaimentService {
    @Autowired
    MoyenPaiementRepository moyenPaiementRepository;

    @Override
    public List<MoyenPaiement> getListMoyen() {
        return moyenPaiementRepository.findAll();
    }

    @Override
    public Optional<MoyenPaiement> findByMoyenId(Long id) {
        return moyenPaiementRepository.findById(id);
    }

    @Override
    public MoyenPaiement save(MoyenPaiement m) {
        return moyenPaiementRepository.save(m);
    }

    @Override
    public void delete(MoyenPaiement m) {
        moyenPaiementRepository.delete(m);
    }
}
