package sn.gainde2000.dii.services.iservice;

import java.util.List;

import sn.gainde2000.dii.models.Importation;

public interface IImportationService {
    Importation save(Importation importation);

    List<Importation> ListeImportations();

    Importation findByNumero(String numero);

    Importation findByNumeroAndEmail(String numero, String email);

    List<Importation> getImportationByUtilisateur(int id);

    String generateNumeroDII();

    Integer generateNumeroFature();
}
