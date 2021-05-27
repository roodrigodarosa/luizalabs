package com.luizalabs.agendamento.builder;

import java.util.Date;

import com.luizalabs.agendamento.controller.dto.SchedulingDTO;

public class SchedulingDTOBuilder {

    private SchedulingDTO schedulingDTO;

    public SchedulingDTOBuilder() {
        schedulingDTO = new SchedulingDTO();
    }

    public static SchedulingDTOBuilder of() {
        return new SchedulingDTOBuilder();
    }

    public SchedulingDTOBuilder id(String id) {
        schedulingDTO.setId(id);
        return this;
    }

    public SchedulingDTOBuilder dateToSend(Date dateToSend) {
        schedulingDTO.setDateToSend(dateToSend);
        return this;
    }

    public SchedulingDTOBuilder recipient(String recipient) {
        schedulingDTO.setRecipient(recipient);
        return this;
    }

    public SchedulingDTOBuilder recipientType(String recipientType) {
        schedulingDTO.setRecipientType(recipientType);
        return this;
    }
    public SchedulingDTOBuilder status(String status) {
        schedulingDTO.setStatus(status);
        return this;
    }

    public SchedulingDTOBuilder message(String message) {
        schedulingDTO.setMessage(message);
        return this;
    }

    public SchedulingDTO build() {
        return schedulingDTO;
    }

}
