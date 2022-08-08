package sn.gainde2000.dii.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import reactor.util.annotation.Nullable;
import sn.gainde2000.dii.models.*;
import sn.gainde2000.dii.services.FileStorageService;
import sn.gainde2000.dii.services.iservice.IImportationService;
import sn.gainde2000.dii.services.iservice.IInfoPPMService;
import sn.gainde2000.dii.services.iservice.IMarchandiseService;
import sn.gainde2000.dii.services.iservice.IUtilisateurService;
import sn.gainde2000.dii.utils.Email;
import sn.gainde2000.dii.utils.OrbusEmail;
import sn.gainde2000.dii.utils.Response;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: dii
 * Package: sn.gainde2000.dii.controllers
 * User: Ilo
 * Date: 30/08/2021
 * Time: 12:34
 * Created with IntelliJ IDEA
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/importation")
@CrossOrigin(origins = "http://localhost:4200")
public class ImportationController {

/*    @Value("${logo-url-folder}")
    String UPLOADED_FOLDER;*/

    private final IImportationService importationService;
    private final IMarchandiseService marchandiseService;
    private final IInfoPPMService iInfoPPMService;
    private final IUtilisateurService utilisateurService;
    private final FileStorageService storageService;
    private final ApplicationContext appContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/create", headers = {"content-type=multipart/form-data"})
    public Response<?> createImportation(@Nullable @RequestPart("document_1") MultipartFile document_1,
                                         @Nullable @RequestPart("document_2") MultipartFile document_2,
                                         @Nullable @RequestPart("document_3") MultipartFile document_3,
                                         @Nullable @RequestPart("document_4") MultipartFile document_4,
                                         @Nullable @RequestPart("document_5") MultipartFile document_5,
                                         @RequestPart("importation") String importation1) throws IOException {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Importation importation = objectMapper.readValue(importation1, Importation.class);
            Utilisateur u = utilisateurService.findByEmail(importation.getEmailImportateur());
            if (importation.getId() == null)
                importation.setNumero(importationService.generateNumeroDII());
            importation.setUtilisateur(u);
            importation.setDateCreation(new Date());
            importation.setIdInvoice(importationService.generateNumeroFature());
            //Email email = new Email();
            //email.sendMail("no-reply@gainde2000.sn", importation.getEmailImportateur(), "Veuillez suivre votre demande avec les identifiants suivants Email : " + importation.getEmailImportateur() + " et Numero Deamnde : " + importation.getNumero(), "Information Suivi de la demande");

            Importation imp = importationService.save(importation);

            if (document_1 != null)
                storageService.store(document_1, imp.getId());
            if (document_2 != null)
                storageService.store(document_2, imp.getId());
            if (document_3 != null)
                storageService.store(document_3, imp.getId());
            if (document_4 != null)
                storageService.store(document_4, imp.getId());
            if (document_5 != null)
                storageService.store(document_5, imp.getId());

            for (Marchandise marchandise : importation.getMarchandise()) {
                marchandise.setImportation(imp);
                marchandiseService.save(marchandise);
            }
            return imp == null ? Response.exception().setErrors("Une erreur est survenue") : Response.ok().setData(imp).setMessage("Importation enregistré avec success");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            String message = "Could not upload the file: " + document_1.getOriginalFilename() + "!";
            return Response.exception().setErrors(message);
        }
    }

    @GetMapping(value = "/all")
    public Response<?> getAllImportation() {
        List<Importation> importationList = importationService.ListeImportations();
        return Response.ok().setData(importationList).setMessage("Liste des Importations");

    }

    @GetMapping(value = "/importationParNumero/{numero}")
    public Response<?> getImportationByNumero(@PathVariable(name = "numero") String numero) {
        Importation importation = importationService.findByNumero(numero);
        return importation == null ? Response.exception().setErrors("Une erreur est survenue") : Response.ok().setData(importation).setMessage("Importation");
    }

    @GetMapping(value = "/importationByNumeroAndEmail/{numero}/{email}")
    public Response<?> getImportationByNumero(@PathVariable(name = "numero") String numero, @PathVariable(name = "email") String email) {
        Importation importation = importationService.findByNumeroAndEmail(numero, email);
        return importation == null ? Response.exception().setErrors("Une erreur est survenue") : Response.ok().setData(importation).setMessage("Importation");
    }

    @GetMapping(value = "/infoppm/{codeppm}")
    public Response<?> getCodePPM(@PathVariable(name = "codeppm") String codeppm) {
        InfoPPM infoPPM = iInfoPPMService.findByCode(codeppm);
        return infoPPM == null ? Response.exception().setErrors("Une erreur est survenue") : Response.ok().setData(infoPPM).setMessage("Information Code PPM");
    }

    @GetMapping(value = "/importationByUser/{id}")
    public Response<?> getImportationByUser(@PathVariable(name = "id") int id) {
        List<Importation> importationList = importationService.getImportationByUtilisateur(id);
        return importationList == null ? Response.exception().setErrors("Une erreur est survenue") : Response.ok().setData(importationList).setMessage("Liste des importations par user");
    }

    @GetMapping(value = "/statistique/{id}")
    public Response<?> getStatistiquebyUser(@PathVariable(name = "id") int id) {
        HashMap<String, Integer> stat = new HashMap<>();

        List<Importation> importationList = importationService.getImportationByUtilisateur(id);
        int soumis = importationList.size();
        int impayer = 0;
        int brouillon = 0;
        int termine = 0;
        for (Importation i : importationList) {
            if (i.getStatus() == 1) {
                impayer++;
            } else if (i.getStatus() == 0) {
                brouillon++;
            } else {
                termine++;
            }
        }
        stat.put("Soumise", soumis);
        stat.put("Brouillon", brouillon);
        stat.put("Impayer", impayer);
        stat.put("Terminer", termine);
        return Response.ok().setData(stat).setMessage("Statistiques Importations");
    }

    @GetMapping(value = "/utilisateurByMail/{mail}")
    public Response<?> getUserByMail(@PathVariable(name = "mail") String mail) {
        Utilisateur utilisateur = utilisateurService.findByEmail(mail);
        return utilisateur == null ? Response.exception().setErrors("Une erreur est survenue") : Response.ok().setData(utilisateur).setMessage("Utilisateur By Mail");
    }


    @GetMapping(value = "/update/{numero}")
    public Response<?> updateImportation(@PathVariable(name = "numero") String numero) {
        Importation importation = importationService.findByNumero(numero);
        importation.setStatus(importation.getStatus() + 1);
        Importation i = importationService.save(importation);
        return i == null ? Response.exception().setErrors("Une erreur est survenue") : Response.ok().setData(i).setMessage("Mise a Jour Importation");
    }


    @GetMapping(value = "/fichierVisualiser/pdf/{numero}")
    public void demandeVisualiser(HttpServletResponse response, @PathVariable("numero") String numero) throws SQLException, JRException {
        Connection dataSource = DriverManager.getConnection("jdbc:mysql://localhost:3306/diidb", "root", "");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("numero", numero);
        String filename = "dii" + numero.trim() + ".pdf";
        String jasperPath = "/Users/mac/Documents/dii/BackEndDII/src/main/resources/fichier/DemandeInitialisationImportation.jasper";
        generatePDF(response, numero, dataSource, params, filename, jasperPath);
    }

    @GetMapping(value = "/fichierQrCode/pdf/{numero}")
    public void demandeQrCode(HttpServletResponse response, @PathVariable("numero") String numero) throws SQLException, JRException {
        Connection dataSource = DriverManager.getConnection("jdbc:mysql://localhost:3306/diidb", "root", "");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("numero", numero);
        String filename = "dii" + numero.trim() + ".pdf";
        String jasperPath = "/Users/mac/Documents/dii/BackEndDII/src/main/resources/fichier/qrcode.jasper";
        generatePDF(response, numero, dataSource, params, filename, jasperPath);
    }

    private void generatePDF(HttpServletResponse response, String numero, Connection dataSource, Map<String, Object> params, String filename, String jasperPath) throws JRException {
        Importation importation = importationService.findByNumero(numero);
        File file = new File(jasperPath);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, dataSource);
        response.addHeader("Content-disposition", "attachement; filename=" + filename);
        byte[] output = JasperExportManager.exportReportToPdf(print);
        importation.setDii(output);
        importationService.save(importation);
    }

    @GetMapping("/downloadFile/{numero}")
    public ResponseEntity<ByteArrayResource> downloadfile(@PathVariable("numero") String numero) {
        Importation data = importationService.findByNumero(numero);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=" + "fichier")
                .body(new ByteArrayResource(data.getDii()));
    }
}
