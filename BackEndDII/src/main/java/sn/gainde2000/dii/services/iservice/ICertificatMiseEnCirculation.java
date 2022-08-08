package sn.gainde2000.dii.services.iservice;

import sn.gainde2000.dii.models.CertificatMiseEnCirculation;

public interface ICertificatMiseEnCirculation {
    CertificatMiseEnCirculation findByNumeroChassis(String numeroChassis);
}
