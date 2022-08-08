/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.dii.paiement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.dii.paiement.entity.OperateurPaiement;
import sn.gainde2000.dii.paiement.serviceimpl.OperateurPaiementServiceImpl;
import sn.gainde2000.dii.utils.Response;

import java.util.List;

/**
 * @author yougadieng
 */
@RestController
@CrossOrigin("*")
@RequestMapping("operateurPaiement")
public class OperateurPaiementController {

    @Autowired
    OperateurPaiementServiceImpl operateurPaiementRepo;

    @GetMapping("list")
    public Response<?> getAll() {
        List<OperateurPaiement> op = operateurPaiementRepo.getAll();
        return op == null ? Response.exception().setErrors("Aucune élèment trouvé.") : Response.ok().setData(op).setMessage("Données récupérées.");
    }

    @PostMapping("create")
    public Response<?> create(@RequestBody OperateurPaiement operateur) {
        operateurPaiementRepo.CreateOperateurPaiement(operateur);
        return Response.ok().setMessage("Operateur crées");
    }

    @GetMapping("activer/{idOpa}")
    public Response<?> activerOperateur(@PathVariable Long idOpa) {
        return Response.ok().setMessage("Operateur activer");
    }

    @GetMapping("desactiver/{idOpa}")
    public Response<?> desactiverOperateur(@PathVariable Long idOpa) {
        operateurPaiementRepo.desactiverOperateurPaiement(idOpa);
        return Response.ok().setMessage("Operateur desactiver");
    }


}
