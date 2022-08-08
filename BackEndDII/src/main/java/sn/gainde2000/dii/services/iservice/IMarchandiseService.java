package sn.gainde2000.dii.services.iservice;

import java.util.List;

import sn.gainde2000.dii.models.Marchandise;

public interface IMarchandiseService {
    Marchandise save(Marchandise marchandise);

    List<Marchandise> listeMarchandises();
}
