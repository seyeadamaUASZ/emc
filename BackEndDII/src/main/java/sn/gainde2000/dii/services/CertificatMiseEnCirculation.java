package sn.gainde2000.dii.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.gainde2000.dii.repositories.CertificatMiseEnCirculationRepository;
import sn.gainde2000.dii.services.iservice.ICertificatMiseEnCirculation;

@Service
@RequiredArgsConstructor
public class CertificatMiseEnCirculation implements ICertificatMiseEnCirculation {
    private final CertificatMiseEnCirculationRepository certificatMiseEnCirculationRepository;

    @Override
    public sn.gainde2000.dii.models.CertificatMiseEnCirculation findByNumeroChassis(String numeroChassis) {
        return null;
    }
}
