/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.dii.paiement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.dii.paiement.entity.OperateurPaiement;
import sn.gainde2000.dii.paiement.repository.OperateurPaiementRepository;
import sn.gainde2000.dii.paiement.service.IOperateurPaiement;

import java.util.List;

/**
 *
 * @author yougadieng
 */
@Service
public class OperateurPaiementServiceImpl implements IOperateurPaiement {
    @Autowired
    OperateurPaiementRepository opaimentRepo;

    @Override
    public List<OperateurPaiement> getAll() {
        return opaimentRepo.findAll();
    }

    @Override
    public void activerOperateurPaiement(Long id) {
        opaimentRepo.activerOperateur(id);
    }

    @Override
    public void desactiverOperateurPaiement(Long id) {
        opaimentRepo.desactiverOperateur(id);
    }

    @Override
    public void supprimerOperateurPaiement(OperateurPaiement operaPaiement) {
        opaimentRepo.delete(operaPaiement);
      
    }

    @Override
    public void CreateOperateurPaiement(OperateurPaiement operateurPaiement) {
        opaimentRepo.save(operateurPaiement);
    }

   
    
}
