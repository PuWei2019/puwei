package com.cy.store.controller.ex;

public class FileUplodaException extends RuntimeException{
    public FileUplodaException() {
        super();
    }

    public FileUplodaException(String message) {
        super(message);
    }

    public FileUplodaException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUplodaException(Throwable cause) {
        super(cause);
    }

    protected FileUplodaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
