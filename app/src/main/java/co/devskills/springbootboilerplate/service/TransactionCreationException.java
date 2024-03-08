package co.devskills.springbootboilerplate.service;

public class TransactionCreationException extends RuntimeException {

    public TransactionCreationException(String message) {
        super(message);
    }

    public TransactionCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
