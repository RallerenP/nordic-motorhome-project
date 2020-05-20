package com.nordicmotorhome.motorhomerentals.domain.exceptions;

public class NoSuchEntityException extends Exception {
    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
