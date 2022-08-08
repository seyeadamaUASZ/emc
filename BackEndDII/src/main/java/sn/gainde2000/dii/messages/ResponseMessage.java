package sn.gainde2000.dii.messages;

/**
 * Project: UploadFileProject
 * Package: sn.gainde2000.dii.messages
 * User: Ilo
 * Date: 02/09/2021
 * Time: 18:31
 * Created with IntelliJ IDEA
 */
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
