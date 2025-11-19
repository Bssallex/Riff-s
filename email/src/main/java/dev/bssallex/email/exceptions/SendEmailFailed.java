package dev.bssallex.email.exceptions;

public class SendEmailFailed extends RuntimeException {
    public SendEmailFailed(String message) {
        super(message);
    }
}
