package com.mayfly.sportsbook.exception;

public class SportBookException extends RuntimeException {

    private static final long serialVersionUID = -4560391293618932594L;

    public SportBookException() {
        super();
    }

    public SportBookException(String message) {
        super(message);
    }

    public SportBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public SportBookException(Throwable cause) {
        super(cause);
    }
}