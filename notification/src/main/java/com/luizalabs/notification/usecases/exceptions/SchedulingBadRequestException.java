package com.luizalabs.notification.usecases.exceptions;

public class SchedulingBadRequestException extends SchedulingException {

    public static final int STATUS = 400;

    public SchedulingBadRequestException(String message) {
        super(message);
    }

    public int getStatus() {
        return STATUS;
    }
}
