package com.nordicmotorhome.motorhomerentals.domain.exceptions;
// AUTHOR: RAP
// An exception used for when an entity doesn't exist or can't be found
public class NoSuchEntityException extends Exception {
    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
