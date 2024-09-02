package services;

import com.mysql.cj.Session;

import java.util.Properties;

import static com.mysql.cj.Session.*;

public class GmailSender {

    public static boolean sendEmail(String to, String from, String subject, String otp) {
        boolean flag = false;


       // Email configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Creating a session with the Gmail SMTP server

//         Session.getIn



        return flag;
    }

}
