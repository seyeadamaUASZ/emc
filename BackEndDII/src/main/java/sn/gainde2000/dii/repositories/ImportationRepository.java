package sn.gainde2000.dii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.models.Importation;

import java.util.List;

@Repository
public interface ImportationRepository extends JpaRepository<Importation, Long> {

    @Query("SELECT MAX(i.id) FROM Importation i")
    Integer getLastID();

    Importation getImportationByNumero(String numero);

    Importation getImportationByNumeroAndEmailImportateur(String numero, String email);

    @Query("SELECT i FROM Importation i WHERE i.utilisateur.id = :id")
    List<Importation> getImportationByUtilisateur(int id);


}
