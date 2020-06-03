package com.nordicmotorhome.motorhomerentals.domain.exceptions;
// AUTHOR: RAP
public class NoSuchEntityException extends Exception {
    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
