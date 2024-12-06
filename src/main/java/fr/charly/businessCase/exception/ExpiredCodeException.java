package fr.charly.businessCase.exception;

public class ExpiredCodeException extends RuntimeException {

    public ExpiredCodeException(String message) {
        super(message);
    }


}
