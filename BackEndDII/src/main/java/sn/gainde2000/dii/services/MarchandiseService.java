package sn.gainde2000.dii.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.gainde2000.dii.models.Marchandise;
import sn.gainde2000.dii.repositories.MarchandiseRepository;
import sn.gainde2000.dii.services.iservice.IMarchandiseService;

@RequiredArgsConstructor
@Service
public class MarchandiseService implements IMarchandiseService {
    @Autowired
    private MarchandiseRepository marchandiseRepository;

    @Override
    public Marchandise save(Marchandise marchandise) {
        return marchandiseRepository.save(marchandise);
    }

    @Override
    public List<Marchandise> listeMarchandises() {
        return marchandiseRepository.findAll();
    }

}
