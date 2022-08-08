package sn.gainde2000.dii.services;

import org.springframework.stereotype.Service;
import sn.gainde2000.dii.services.iservice.ISimpleEmailService;
import sn.gainde2000.dii.utils.OrbusEmail;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class SimpleEmailService implements ISimpleEmailService {


    private ScheduledExecutorService
            quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads); //Creates a thread pool that reuses fixed number of threads(as specifie  noOfThreads in this case).
    public static int noOfQuickServiceThreads = 20;

    @Override
    public void sendEmail(String destinataie, String objet, String text) {

        quickService.submit(new Runnable() {

            @Override
            public void run() {
                try {

                    System.out.println("Debut");
                    // emailSender.sendMail();
                    OrbusEmail.sendHtmlMessage(objet, text, destinataie);

                    System.out.println("Fin");
                    //sendSms(telephone, texte) ;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });


    }

}
