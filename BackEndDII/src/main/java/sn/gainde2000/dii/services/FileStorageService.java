package sn.gainde2000.dii.services;

import sn.gainde2000.dii.repositories.FileDBRepository;
import sn.gainde2000.dii.models.FileDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Project: UploadFileProject
 * Package: sn.gainde2000.dii.services
 * User: Ilo
 * Date: 02/09/2021
 * Time: 18:28
 * Created with IntelliJ IDEA
 */
@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file, Long id) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), id);

        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
