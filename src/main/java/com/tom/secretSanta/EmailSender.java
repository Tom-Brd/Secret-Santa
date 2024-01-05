package com.tom.secretSanta;

import io.github.cdimascio.dotenv.Dotenv;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private final Dotenv dotenv = Dotenv.configure().filename(".env.local").ignoreIfMalformed().ignoreIfMissing().load();
    private String username;
    private String password;

    public EmailSender() {
        setUsername();
        setPassword();
    }

    public EmailSender(Session sessionMock) {}

    public void setUsername() {
        this.username = dotenv.get("USERNAME");
    }

    public void setPassword() {
        this.password = dotenv.get("PASSWORD");
    }

    public void sendEmail(String recipient, String subject, String body) throws MessagingException {
        Session session = createEmailSession();
        Message message = prepareMessage(session, recipient, subject, body);
        Transport.send(message);
    }

    private Session createEmailSession() {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(prop, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });
    }

    private Message prepareMessage(Session session, String recipient, String subject, String body) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);
        return message;
    }
}
