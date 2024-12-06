package fr.charly.businessCase.exception;

public class AlreadyActiveException extends RuntimeException {

    public AlreadyActiveException(String message) {
        super(message);
    }
}
