package sn.gainde2000.dii.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.models.Utilisateur;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Page<Utilisateur> findAllByOrderByIdDesc(Pageable pageable);

    Optional<Utilisateur> findUtilisateurByEmail(String userName);

    @Query("SELECT u FROM Utilisateur u WHERE u.email = :email")
    Utilisateur findByEmail(String email);

    Utilisateur findByTelephone(String telephone);

    Optional<Utilisateur> findById(int id);

    List<Utilisateur> findAllByEmail(String email);


}
