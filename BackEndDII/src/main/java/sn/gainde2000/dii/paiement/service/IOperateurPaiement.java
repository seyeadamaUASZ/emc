/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.dii.paiement.service;


import sn.gainde2000.dii.paiement.entity.OperateurPaiement;

import java.util.List;

/**
 *
 * @author yougadieng
 */
public interface IOperateurPaiement {
    public List<OperateurPaiement> getAll();
    public void activerOperateurPaiement(Long id);
    public void CreateOperateurPaiement(OperateurPaiement oprateurPaiement);
    public void desactiverOperateurPaiement(Long id);
    public void supprimerOperateurPaiement(OperateurPaiement operaPaiement);

    
}
