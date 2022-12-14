package sn.gainde2000.dii.utils;

import java.util.List;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Project: BackEndDII
 * Package: sn.gainde2000.dii.utils
 * User: Ilo
 * Date: 06/09/2021
 * Time: 09:51
 * Created with IntelliJ IDEA
 */
@Component
public class Email {







    public static final String MAIL_HOST = "192.168.1.49";
    /* private static final String FROM = "no.reply.industrialisation@gmail.com";
    private static final String PASSWORD = "no.reply.industrialisation@2000!";*/
    private static final String FROM = "no-reply@gainde2000.sn";
    private static final String PASSWORD = "";
    public void sendMail(String from, String to, String message, String subject) throws MessagingException {
        //System.out.println("----------------------AppProperties.getHostmail()-----------------------"+app.getHostmail());
        Properties props = new Properties();



       /* props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");*/
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", "192.168.1.49");
        props.put("mail.smtp.port", "2020");
        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);
        //System.out.println("----------------------From adress----------------------- "+from);
        if(from!=null){
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
      /* Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
          });
        MimeMessage msg = new MimeMessage(session);
        //System.out.println("----------------------From adress----------------------- "+from);
        if(from!=null){
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(message);
           // Transport.send(msg);
            Transport tr = session.getTransport("smtp");
            tr.connect("smtp.gmail.com", FROM, PASSWORD);
            msg.saveChanges(); // don't forget this
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();*/



        } else {
            //msg.setFrom(new InternetAddress(app.getFrommail()));
            System.out.println("----------------------Erreur envoi de message----------------------- "+message);
        }

        //System.out.println("----------------------message----------------------- "+message+" ----------------------subject----------------------- "+subject+" ----------------------destinataire-----------------------"+to);
    }
    public boolean sendMessage2(String subject, String text, String destinataire) {
        boolean isSend = false;
        try {
            sendMail(FROM, destinataire, text, subject);
            isSend = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return isSend;
    }



    public void sendMessage(String subject, String text, String destinataire) {
        try {
            sendMail(FROM, destinataire, text, subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



    public void sendHtmlMail(String password, String from, String to, String message, String subject) throws MessagingException {



        //System.out.println("message html");
        Properties props = new Properties();
        props.put("mail.smtp.host", MAIL_HOST);



        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(message, "utf-8", "html");
        Transport.send(msg);
    }



    public void broadcastHtmlMail(String password, String from, List<String> to, String message, String subject) throws MessagingException {



        //System.out.println("message html");



        Properties props = new Properties();
        props.put("mail.smtp.host", MAIL_HOST);



        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        for (int i = 0; i < to.size(); i++) {
            msg.addRecipients(RecipientType.TO, InternetAddress.parse(to.get(i)));
        }
        msg.setSubject(subject);
        msg.setText(message, "utf-8", "html");
        Transport.send(msg);
    }



    public boolean sendHtmlMessage(String subject, String text, String destinataire, String link) {
        boolean isSend = false;

        String message = "<div style=\"width:600px;height: 609px;border-radius: 5px;border: solid 1px #0f569d;background-color: #f6f6f6;\">\n"
                + "<div style=\"width: 100%;height: 68px;border-bottom: : solid 1px #979797;background-color: #0f569d; position: relative;top:0;;background: #0f569d;\">\n"
                + "    <div style=\"align:center;padding:5px;display:block;font-family: TrebuchetMS;font-size: 21px;font-weight: normal;font-style: normal;font-stretch: normal;line-height: normal;letter-spacing: normal;text-align: center;color: #ffffff;\">Overflight / landing and stopover authorization request platform</div>\n"
                + "    \n"
                + "    </div>\n"
                + "    <div style=\"margin:25px;width: 91%;height: 80%;border: solid 0.5px #979797;background-color: #ffffff;\">\n"
                + "        <div style=\"display: block; text-align: center; margin: 5px; font-family: TrebuchetMS;font-size: 21px;font-weight: bold;font-style: normal;font-stretch: normal;line-height: normal;letter-spacing: normal;color: #0f569d;\">\n"
                + subject + "\n"
                + "        </div>\n"
                + "        <div style=\"margin: 10px;margin-top:20px;  font-family: TrebuchetMS;font-size: 21px;font-weight: normal;font-style: normal;font-stretch: normal;line-height: 1.43;letter-spacing: normal;color: rgba(0, 0, 0, 0.85);\">\n"
                + text
                + "        </div><br/><br/><br/>\n\n\n\n"
                + "        <center><a href=\""+link+"\" style=\"text-decoration:none;display:block;width: 90%;height: 20px;border: solid 1px #979797;background-color: #0f569d; color:white;margin-top: 50px;cursor: pointer;padding: 20px;\">Login</a></center>\n"
                + "    </div>\n"
                + "</div>";
        try {
            sendHtmlMail(PASSWORD, FROM, destinataire, message, subject);
            isSend = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return isSend;
    }



    public boolean broadcastHtmlMessage(String subject, String text, List<String> destinataire, String link) {
        boolean isSend = false;



        String message = "<div style=\"width:600px;height: 609px;border-radius: 5px;border: solid 1px #0f569d;background-color: #f6f6f6;\">\n"
                + "<div style=\"width: 100%;height: 68px;border-bottom: : solid 1px #979797;background-color: #0f569d; position: relative;top:0;;background: #0f569d;\">\n"
                + "    <div style=\"align:center;padding:5px;display:block;font-family: TrebuchetMS;font-size: 21px;font-weight: normal;font-style: normal;font-stretch: normal;line-height: normal;letter-spacing: normal;text-align: center;color: #ffffff;\">Overflight / landing and stopover authorization request platform</div>\n"
                + "    \n"
                + "    </div>\n"
                + "    <div style=\"margin:25px;width: 91%;height: 80%;border: solid 0.5px #979797;background-color: #ffffff;\">\n"
                + "        <div style=\"display: block; text-align: center; margin: 5px; font-family: TrebuchetMS;font-size: 21px;font-weight: bold;font-style: normal;font-stretch: normal;line-height: normal;letter-spacing: normal;color: #0f569d;\">\n"
                + subject + "\n"
                + "        </div>\n"
                + "        <div style=\"margin: 10px;margin-top:20px;  font-family: TrebuchetMS;font-size: 21px;font-weight: normal;font-style: normal;font-stretch: normal;line-height: 1.43;letter-spacing: normal;color: rgba(0, 0, 0, 0.85);\">\n"
                + text
                + "        </div>\n\n\n\n"
                + "        <center><a href=\""+link+"\" style=\"text-decoration:none;display:block;width: 90%;height: 20px;border: solid 1px #979797;background-color: #0f569d; color:white;margin-top: 50px;cursor: pointer;padding: 20px;\">Login</a></center>\n"
                + "    </div>\n"
                + "</div>";
        try {
            broadcastHtmlMail(PASSWORD, FROM, destinataire, message, subject);
            isSend = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return isSend;
    }

}
