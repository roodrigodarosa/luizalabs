package com.luizalabs.agendamento.service.exception;

public class SchedulingNotFoundException extends SchedulingException {

    public static final int STATUS = 404;

    public SchedulingNotFoundException(String message) {
        super(message);
    }

    public int getStatus() {
        return STATUS;
    }
}
