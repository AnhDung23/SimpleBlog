/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.email;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sun.util.logging.resources.logging;

/**
 *
 * @author Admin
 */
public class Email implements Serializable {

    private String email;
    private String code;

    public Email(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public void senMail() {
        // My email
        String email = "adung5341@gmail.com";  // mail nguoi gui
        String pass = "vhijsxfmeoskgngl"; // mat khau email

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Verification account");
            message.setText("Your Vefification code: " + code);
            //send mesage
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
