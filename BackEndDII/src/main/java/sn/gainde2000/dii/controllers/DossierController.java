package sn.gainde2000.dii.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.dii.models.Dossier;
import sn.gainde2000.dii.services.iservice.IDossier;
import sn.gainde2000.dii.utils.Response;

@RestController
@RequestMapping("dossiers")
@AllArgsConstructor
public class DossierController {
    private final IDossier iDossier;

    @PostMapping
    public Response<?> createDossier(@RequestBody Dossier dossier){
        Dossier doc = iDossier.createDossier(dossier);

        return Response.ok().setData(doc);
    }

    @GetMapping("search/numero")
    public Response<?> searchByNumero(@RequestParam("numero") String numero){
        Dossier dossier = iDossier.findByNumero(numero);
        return Response.ok().setData(dossier);
    }


    @GetMapping("search/chassis")
    public Response<?> searchByChassis(@RequestParam("chassis") String chassi){
        Dossier dossier = iDossier.findByNumeroChassis(chassi);
        return Response.ok().setData(dossier);
    }
}
