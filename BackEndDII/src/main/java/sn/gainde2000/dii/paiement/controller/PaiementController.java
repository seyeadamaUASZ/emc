/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.dii.paiement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.dii.paiement.entity.Paiement;
import sn.gainde2000.dii.paiement.serviceimpl.PaiementServiceImpl;
import sn.gainde2000.dii.utils.Response;

/**
 * @author yougadieng
 */
@RestController
@CrossOrigin("*")
@RequestMapping("paiement")
public class PaiementController {
    @Autowired
    PaiementServiceImpl paiementService;

    @GetMapping("list")
    public Response<?> getAllTransactions() {
        Iterable<Paiement> paiement = paiementService.listOperation();
        return paiement == null ? Response.exception().setErrors("Aucune élèment trouvé.") : Response.ok().setData(paiement).setMessage("Données récupérées.");
    }

    @GetMapping("list/{username}")
    public Response<?> getTransactionByReferenceClient(@PathVariable String username) {
        Iterable<Paiement> paiement = paiementService.listOperationByReferenceClient(username);
        return paiement == null ? Response.exception().setErrors("Aucune élèment trouvé.") : Response.ok().setData(paiement).setMessage("Données récupérées.");
    }

    @GetMapping("referencePaiement/{idFacture}")
    public Response<?> getPaiementByReference(@PathVariable Long idFacture) {
        Paiement paiement = paiementService.findByIdFacture(idFacture);
        return paiement == null ? Response.exception().setErrors("Aucune élèment trouvé.") : Response.ok().setData(paiement).setMessage("Données récupérées.");
    }

}
