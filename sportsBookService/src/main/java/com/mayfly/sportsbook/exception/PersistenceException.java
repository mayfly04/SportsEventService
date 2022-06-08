package com.mayfly.sportsbook.exception;


public class PersistenceException extends SportBookException {
    private static final long serialVersionUID = 1396814242721961120L;

    public PersistenceException(String message, Throwable error) {
        super(message, error);
    }

    public PersistenceException() {
        super();
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(Throwable error) {
        super(error);
    }
}