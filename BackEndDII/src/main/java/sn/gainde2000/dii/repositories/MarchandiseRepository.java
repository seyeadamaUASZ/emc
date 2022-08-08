package sn.gainde2000.dii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.models.Marchandise;

@Repository
public interface MarchandiseRepository extends JpaRepository<Marchandise, Long> {

}
