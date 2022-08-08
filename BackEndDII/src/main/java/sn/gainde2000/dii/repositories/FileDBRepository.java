package sn.gainde2000.dii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.dii.models.FileDB;

/**
 * Project: UploadFileProject
 * Package: sn.gainde2000.dii.Repositories
 * User: Ilo
 * Date: 02/09/2021
 * Time: 18:27
 * Created with IntelliJ IDEA
 */
@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}