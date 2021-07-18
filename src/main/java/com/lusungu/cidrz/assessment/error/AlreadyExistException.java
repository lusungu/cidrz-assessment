package com.lusungu.cidrz.assessment.error;

public class AlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistException(final String message) {
        super(message);
    }

    public AlreadyExistException(final Throwable cause) {
        super(cause);
    }

}
