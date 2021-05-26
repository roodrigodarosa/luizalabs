package com.luizalabs.agendamento.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.luizalabs.agendamento.domain.Scheduling;
import com.luizalabs.agendamento.exception.SchedulingBadRequestException;

@Service
public class SchedulingValidator {

    public void validate(Scheduling scheduling) throws SchedulingBadRequestException {
        validateMandatoryFields(scheduling);
        validateDateToSend(scheduling.getDateToSend());
    }

    private void validateMandatoryFields(Scheduling scheduling) throws SchedulingBadRequestException {
        if (scheduling == null) {
            throw new SchedulingBadRequestException("Scheduling está nulo.");
        }
        if (scheduling.getDateToSend() == null) {
            throw new SchedulingBadRequestException("Data de está nulo.");
        }
        if (scheduling.getRecipient() == null || scheduling.getRecipient().isEmpty()) {
            throw new SchedulingBadRequestException("Destinatário está nulo ou vazio.");
        }
        if (scheduling.getMessage() == null || scheduling.getMessage().isEmpty()) {
            throw new SchedulingBadRequestException("Mensagem está nula ou vazia.");
        }
        if (scheduling.getRecipientType() == null) {
            throw new SchedulingBadRequestException("Tipo de destinatário está nulo");
        }
        if (scheduling.getStatus() == null) {
            throw new SchedulingBadRequestException("Status do agendamento está nulo");
        }
    }

    private void validateDateToSend(Date date) throws SchedulingBadRequestException {
        if (date.getTime() < new Date().getTime()) {
            throw new SchedulingBadRequestException("Data de envio do agendamento deve ser maior que a data atual.");
        }
    }


}
