package it.xpug.kata.birthdaygreetings.services;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import it.xpug.kata.birthdaygreetings.exceptions.NotificationRuntimeException;
import it.xpug.kata.birthdaygreetings.beans.NotificationMessage;
import it.xpug.kata.birthdaygreetings.services.impl.MailService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.mail.Message;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MailServiceTest {
    private static final int NONSTANDARD_PORT = 9999;
    private SimpleSmtpServer mailServer;
    private MailService mailService;

    @Before
    public void setUp() throws IOException {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
        mailService = new MailService("localhost", NONSTANDARD_PORT);
    }

    @After
    public void tearDown() throws Exception {
        mailServer.stop();
        Thread.sleep(200);
    }

    @Test
    public void testConstructor() {
        Assert.assertThrows("with null smtpHost", NotificationRuntimeException.class, () -> new MailService(null, 1));
        Assert.assertThrows("with empty string smtpHost", NotificationRuntimeException.class, () -> new MailService("", 1));
        Assert.assertThrows("with invalid port", NotificationRuntimeException.class, () -> new MailService("localhost", -1));
    }

    @Test
    public void testSendMessage() {
        mailService.sendMessage(createSimpleMailMessage());
        assertEquals("message sent", 1, mailServer.getReceivedEmails().size());
        SmtpMessage message = mailServer.getReceivedEmails().get(0);
        assertEquals("test from", "from@test.it", message.getHeaderValue("From"));
        assertEquals("test body", "test body", message.getBody());
        assertEquals("test subject", "test subject", message.getHeaderValue("Subject"));
        assertEquals("test to", "test@test.it", message.getHeaderValue(Message.RecipientType.TO.toString()));
    }

    private NotificationMessage createSimpleMailMessage() {
        return new NotificationMessage() {
            @Override
            public String getBody() {
                return "test body";
            }

            @Override
            public String getSubject() {
                return "test subject";
            }

            @Override
            public String getFrom() {
                return "from@test.it";
            }

            @Override
            public String getRecipient() {
                return "test@test.it";
            }
        };
    }
}
