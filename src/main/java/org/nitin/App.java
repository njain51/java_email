package org.nitin;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        System.out.println("Preparing to Send Message");

        // define properties needed

        String message = "this is message for Security check only ";
        String subject = "CodersArea: Confirmation";
        String recipent_add = "off****@gmail.com";
        String sender_add = "nitin****@gmail.com";

        // Method to send email with simple text
        sendEmail(message, subject, recipent_add, sender_add);

        // Method to send email with simple text and  with attachment
        sendAttach(message, subject, recipent_add, sender_add);


    }

    // this method is used to send email with attachment
    private static void sendAttach(String message, String subject, String recipent_add, String sender_add) {

        // variable for email host
        String host = "smtp.gmail.com";

        // Get the System properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES" + properties);

        // setting important information properties object

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465"); // default port for google smtp is 465
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.auth", true);


        // Step 1: Get the Session object

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender_add, "*****");
            }
        });

        session.setDebug(true); // setting debug as True

        // Step2: Compose the message

        MimeMessage m = new MimeMessage(session);
        try {
            //from email
            m.setFrom(sender_add);

            // to email
            // InternetAddress - this class represents an Internet email Address using the syntax of RFC822
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(recipent_add));

            // adding subject to message
            m.setSubject(subject);



            // adding attachment C:\Users\offic\OneDrive\Low_Code_Today.pdf

            String path = "C:/Users/offic/OneDrive/Desktop/Low_Code_Today.pdf";
            MimeMultipart m1 = new MimeMultipart();

            MimeBodyPart textmime = new MimeBodyPart();
            MimeBodyPart filemine = new MimeBodyPart();

            try {
                textmime.setText(message);
                File file = new File(path);
                filemine.attachFile(file);

                m1.addBodyPart(textmime);
                m1.addBodyPart(filemine);



            }
            catch (Exception e) {

            }

            m.setContent(m1);

            // Step 3: Send message using Transport class

            Transport.send(m);

            System.out.println("Message sent successfully");


        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

    // this method is used for sending email
    private static void sendEmail(String message, String subject, String recipent_add, String sender_add) {

        // variable for email host
        String host = "smtp.gmail.com";

        // Get the System properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES" + properties);

        // setting important information properties object

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465"); // default port for google smtp is 465
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.auth", true);


        // Step 1: Get the Session object

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender_add, "*****");
            }
        });

        session.setDebug(true); // setting debug as True

        // Step2: Compose the message

        MimeMessage m = new MimeMessage(session);
        try {
            //from email
            m.setFrom(sender_add);

            // to email
            // InternetAddress - this class represents an Internet email Address using the syntax of RFC822
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(recipent_add));

            // adding subject to message
            m.setSubject(subject);

            // adding body to message
            m.setText(message);


            // Step 3: Send message using Transport class

            Transport.send(m);

            System.out.println("Message sent successfully");


        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
