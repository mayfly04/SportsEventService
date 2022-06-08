package com.mayfly.sportsbook.exception;


public class SportbookResourceNotFound extends SportBookException {
    
	private static final long serialVersionUID = 4874276952828362840L;

	public SportbookResourceNotFound(String message, Throwable error) {
        super(message, error);
    }

    public SportbookResourceNotFound() {
        super();
    }

    public SportbookResourceNotFound(String message) {
        super(message);
    }

    public SportbookResourceNotFound(Throwable error) {
        super(error);
    }
}