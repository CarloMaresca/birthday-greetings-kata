package it.xpug.kata.birthdaygreetings.beans;

public class BirthdayMessage implements NotificationMessage {
    private static final String TEMPLATE_BODY = "Happy Birthday, dear %s!";
    private static final String TEMPLATE_SUBJECT = "Happy Birthday!";
    private static final String SENDER_MAIL = "sender@here.com";

    private final String body;
    private final String recipient;

    public BirthdayMessage(Employee employee) {
        this.body = String.format(TEMPLATE_BODY, employee.getFirstName());
        this.recipient = employee.getEmail();
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getSubject() {
        return TEMPLATE_SUBJECT;
    }

    @Override
    public String getFrom() {
        return SENDER_MAIL;
    }

    @Override
    public String getRecipient() {
        return recipient;
    }
}
