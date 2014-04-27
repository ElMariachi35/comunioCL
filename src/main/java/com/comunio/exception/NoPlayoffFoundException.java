package com.comunio.exception;

public class NoPlayoffFoundException extends RuntimeException {
    private static final long serialVersionUID = 7855765150172238512L;

    public NoPlayoffFoundException(String message) {
	super(message);
    }

}
