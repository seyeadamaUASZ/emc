package sn.gainde2000.dii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.models.Importation;
import sn.gainde2000.dii.models.InfoPPM;

/**
 * Project: dii
 * Package: sn.gainde2000.dii.repositories
 * User: Ilo
 * Date: 31/08/2021
 * Time: 10:36
 * Created with IntelliJ IDEA
 */
@Repository
public interface InfoPPMRepositories extends JpaRepository<InfoPPM, Long> {

    InfoPPM getInfoPPMByCodeppm(String codePPM);
}
