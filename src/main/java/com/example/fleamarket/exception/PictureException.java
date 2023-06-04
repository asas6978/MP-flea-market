package com.example.fleamarket.exception;

public class PictureException extends DBException{
    public PictureException() {
        super();
    }

    public PictureException(String message) {
        super(message);
    }

    public PictureException(String message, Throwable cause) {
        super(message, cause);
    }

    public PictureException(Throwable cause) {
        super(cause);
    }
}
