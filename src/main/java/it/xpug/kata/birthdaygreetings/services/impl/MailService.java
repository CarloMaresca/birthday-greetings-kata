package it.xpug.kata.birthdaygreetings.services.impl;

import it.xpug.kata.birthdaygreetings.exceptions.NotificationRuntimeException;
import it.xpug.kata.birthdaygreetings.services.NotificationService;
import it.xpug.kata.birthdaygreetings.beans.NotificationMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    private final Session session;

    public MailService(String smtpHost, int smtpPort) {
        if(StringUtils.isEmpty(smtpHost)) throw new NotificationRuntimeException("smtpHost is required");
        if(smtpPort < 0) throw new NotificationRuntimeException("smtpPort is greater than zero");

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));
        session = Session.getInstance(props, null);
    }

    @Override
    public void sendMessage(NotificationMessage mailMessage) {
        try {
            Message msg = createMessage(mailMessage);
            log.info("try to send message with subject: '{}' to recipients: '{}'", msg.getSubject(), msg.getAllRecipients());
            Transport.send(msg);
        } catch (MessagingException e) {
            log.error("error during sendMessage", e);
        }
    }

    private Message createMessage(NotificationMessage mailMessage) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mailMessage.getFrom()));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailMessage.getRecipient()));
        msg.setSubject(mailMessage.getSubject());
        msg.setText(mailMessage.getBody());

        return msg;
    }
}