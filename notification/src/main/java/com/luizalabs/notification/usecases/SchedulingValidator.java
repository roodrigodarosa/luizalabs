package com.luizalabs.notification.usecases;

import java.util.Date;

import com.luizalabs.notification.adapters.repository.entity.Scheduling;
import com.luizalabs.notification.usecases.exceptions.SchedulingBadRequestException;

public class SchedulingValidator {

    public static void validate(Scheduling scheduling) throws SchedulingBadRequestException {
        validateMandatoryFields(scheduling);
        validateDateToSend(scheduling.getScheduledDate());
    }

    private static void validateMandatoryFields(Scheduling scheduling) throws SchedulingBadRequestException {
        if (scheduling == null) {
            throw new SchedulingBadRequestException("Scheduling está nulo.");
        }
        if (scheduling.getScheduledDate() == null) {
            throw new SchedulingBadRequestException("Data de agendamento está nulo.");
        }
        if (scheduling.getRecipient() == null || scheduling.getRecipient().isEmpty()) {
            throw new SchedulingBadRequestException("Destinatário está nulo ou vazio.");
        }
        if (scheduling.getMessage() == null || scheduling.getMessage().isEmpty()) {
            throw new SchedulingBadRequestException("Mensagem está nula ou vazia.");
        }
        if (scheduling.getNotificationType() == null) {
            throw new SchedulingBadRequestException("Tipo de destinatário está nulo.");
        }
        if (scheduling.getStatus() == null) {
            throw new SchedulingBadRequestException("Status do agendamento está nulo.");
        }
    }

    private static void validateDateToSend(Date date) throws SchedulingBadRequestException {
        if (date.getTime() < new Date().getTime()) {
            throw new SchedulingBadRequestException("Data de envio do agendamento deve ser maior que a data atual.");
        }
    }


}
