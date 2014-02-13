package es.yell.paol.test.test;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	public static void main(String[] s){
		try {

            Address fromAddress = new InternetAddress("venkateswara.madugula2@hibu.com", "Venkat");
            Address toAddress = new InternetAddress("venkateswara.madugula2@hibu.com");

            Properties props = new Properties();
            props.put("mail.smtp.host", "USCDREXCAY01.yellglobal.net");
            props.put("mail.smtp.auth", "true");

            String port = "25";
            props.put("mail.smtp.port", port);

            String timeOut = "10000";
            // tiempo de espera de conexión al socket en milisegundos.
            props.put("mail.smtp.connectiontimeout", timeOut);
            // tiempo de espera para el envio/recepcion de datos.
            props.put("mail.smtp.timeout", timeOut);

            Session mailConnection = Session.getInstance(props);

            if ("true".equalsIgnoreCase("true")) {
                mailConnection.setDebug(true);
            }

            // Define message
            MimeMessage msg = new MimeMessage(mailConnection);
            msg.setFrom(fromAddress);
            msg.addRecipient(Message.RecipientType.TO, toAddress);
            msg.setSubject("Test", "UTF-8");
            msg.setSentDate(new Date());

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("hello", "text/html; charset=UTF-8");

            // Creat multipart and add text
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Add attachments
            
            // Put parts in multipart
            msg.setContent(multipart);

            // Save changes
            msg.saveChanges();

            // Send the message
            if ("true".equalsIgnoreCase("true")) {
                Transport t = mailConnection.getTransport("smtp");

                try {
                    t.connect("venkateswara.madugula2@hibu.com", "P@ssword555");
                    t.sendMessage(msg, msg.getAllRecipients());
                } finally {
                    t.close();
                }
            } else {
                Transport.send(msg);
            }

        } catch (UnsupportedEncodingException ue) {
        	ue.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        } catch (Exception se) {
            System.out.println(se);
        }
	}
}
