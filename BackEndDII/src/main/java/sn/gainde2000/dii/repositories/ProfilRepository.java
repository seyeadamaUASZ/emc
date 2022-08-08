package sn.gainde2000.dii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.models.Profil;
import sn.gainde2000.dii.models.Utilisateur;

/**
 * Project: dii
 * Package: sn.gainde2000.dii.repositories
 * User: Ilo
 * Date: 01/09/2021
 * Time: 18:02
 * Created with IntelliJ IDEA
 */
@Repository
public interface ProfilRepository extends JpaRepository<Profil, Integer> {

}
