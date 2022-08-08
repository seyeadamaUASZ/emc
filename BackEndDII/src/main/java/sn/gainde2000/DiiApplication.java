package sn.gainde2000;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import sn.gainde2000.dii.models.Profil;
import sn.gainde2000.dii.services.ProfilService;

import javax.annotation.Resource;

@SpringBootApplication
public class DiiApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Resource
    ProfilService profilService;

    public static void main(String[] args) {
        SpringApplication.run(DiiApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        /*profilService.save(new Profil("Demandeur", "Demandeur"));
        profilService.save(new Profil("Admin", "Administrateur"));*/
    }

}
