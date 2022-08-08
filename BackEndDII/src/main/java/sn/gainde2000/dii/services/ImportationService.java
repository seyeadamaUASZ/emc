package sn.gainde2000.dii.services;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import sn.gainde2000.dii.models.Importation;
import sn.gainde2000.dii.repositories.ImportationRepository;
import sn.gainde2000.dii.services.iservice.IImportationService;

@RequiredArgsConstructor
@Service
public class ImportationService implements IImportationService {

    private final ImportationRepository importationRepository;

    @Override
    public Importation save(Importation importation) {
        return importationRepository.save(importation);
    }

    @Override
    public List<Importation> ListeImportations() {
        return importationRepository.findAll();
    }

    @Override
    public Importation findByNumero(String numero) {
        return importationRepository.getImportationByNumero(numero);
    }

    @Override
    public Importation findByNumeroAndEmail(String numero, String email) {
        return importationRepository.getImportationByNumeroAndEmailImportateur(numero, email);
    }

    @Override
    public List<Importation> getImportationByUtilisateur(int id) {
        return importationRepository.getImportationByUtilisateur(id);
    }

    @Override
    public String generateNumeroDII() {
        Integer in = importationRepository.getLastID();
        System.out.println("Max ID : " + in);
        if (in == null) {
            in = 1;
        } else {
            in++;
        }
        System.out.println("Max ID : " + in);

        DecimalFormat df = new DecimalFormat("000");

        String c = df.format(in);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        System.out.println("Numero : " + date + c);

        return "DII" + date + c;
    }

    @Override
    public Integer generateNumeroFature() {
        int start = ThreadLocalRandom.current().nextInt(100, 999);
        int end = ThreadLocalRandom.current().nextInt(100, 999);
        DecimalFormat df = new DecimalFormat("000");
        String s = df.format(start);
        String e = df.format(end);
        return Integer.parseInt(s + e);
    }
}
