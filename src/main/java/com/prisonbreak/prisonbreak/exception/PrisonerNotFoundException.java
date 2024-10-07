package com.prisonbreak.prisonbreak.exception;

public class PrisonerNotFoundException extends RuntimeException {
    public PrisonerNotFoundException(String message) {
        super(message);
    }
}