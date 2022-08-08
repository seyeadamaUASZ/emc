package sn.gainde2000.dii.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageService {



    private static final String IMAGE_PATTERN
            = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    private static final String[] autorised_extensions = {"JPG", "JPEG", "PNG", "GIF"};
    private static long maxSize = 13000000;



    public static  Map<String, String> storeFileBis(String type, MultipartFile file, String directory) {
        String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));
        Map<String, String> model = new HashMap<>();
        if (!isSequenceOnTable(autorised_extensions, extension)) {
            model.put("message", "L'extension n'est pas autorisé");
            return model;
        }
        if (file.getSize() > maxSize) {
            model.put("message", "La taille est largement dépassée");
            return model;
        }
        try {

            Clock clock = Clock.systemDefaultZone();
            String name = type + "_" + clock.instant().toString().replaceAll(":", "_") + "_" + RandomStringUtils.random(10, true, true) + file.getOriginalFilename();
            Path filePath = Paths.get(directory,name);
            Files.copy(file.getInputStream(), filePath);
            model.put("status", "true");
            model.put("file", name);
            return model;
        } catch (Exception ex) {
            Logger.getLogger(StorageService.class.getName()).log(Level.SEVERE, null, ex);
            model.put("message","Erreur du serveur");
            return model;
        }
    }




    private static String getExtension(String fileName) {
        String extension = "";



        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }



    private static boolean isSequenceOnTable(String[] table, String sequence) {
        for (int i = 0; i < table.length; i++) {
            if (table[i].equalsIgnoreCase(sequence)) {
                return true;
            }
        }
        return false;
    }



}
