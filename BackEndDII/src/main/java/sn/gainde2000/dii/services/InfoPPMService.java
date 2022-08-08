package sn.gainde2000.dii.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.gainde2000.dii.models.InfoPPM;
import sn.gainde2000.dii.repositories.InfoPPMRepositories;
import sn.gainde2000.dii.services.iservice.IInfoPPMService;

/**
 * Project: dii
 * Package: sn.gainde2000.dii.service
 * User: Ilo
 * Date: 31/08/2021
 * Time: 10:37
 * Created with IntelliJ IDEA
 */

@RequiredArgsConstructor
@Service
public class InfoPPMService implements IInfoPPMService {

    private final InfoPPMRepositories infoPPMRepositories;

    @Override
    public InfoPPM findByCode(String codePPM) {
        return infoPPMRepositories.getInfoPPMByCodeppm(codePPM);
    }
}
