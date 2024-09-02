package services;

public class SendGeneratedOTP {



    public static void sendOtp(String email, String genotp) {

        GmailSender gm = new GmailSender();
        String to = email;
        String from = "urvashiy678@gmail.com";
        String subject = "Send email using gmail";
        String otp = genotp ;
        boolean ans = gm.sendEmail(to, from, subject, otp);

        if(ans)
        {
            System.out.println(" Email send successfully...");
        }
        else
        {
            System.out.println("There is problem in sending...");
        }

    }





}
