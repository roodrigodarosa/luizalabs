package com.luizalabs.agendamento.controller.builder;

import java.util.Date;

import com.luizalabs.agendamento.domain.Scheduling;
import com.luizalabs.agendamento.enums.RecipientTypeEnum;
import com.luizalabs.agendamento.enums.StatusEnum;

public class SchedulingBuilder {

    private Scheduling scheduling;

    private SchedulingBuilder() {
        scheduling = new Scheduling();
    }

    public static SchedulingBuilder of() {
        return new SchedulingBuilder();
    }

    public SchedulingBuilder id(String id) {
        scheduling.setId(id);
        return this;
    }

    public SchedulingBuilder dateToSend(Date dateToSend) {
        scheduling.setDateToSend(dateToSend);
        return this;
    }

    public SchedulingBuilder recipient(String recipient) {
        scheduling.setRecipient(recipient);
        return this;
    }

    public SchedulingBuilder recipientType(RecipientTypeEnum recipientTypeEnum) {
        scheduling.setRecipientType(recipientTypeEnum);
        return this;
    }

    public SchedulingBuilder status(StatusEnum statusEnum) {
        scheduling.setStatus(statusEnum);
        return this;
    }

    public SchedulingBuilder message(String message) {
        scheduling.setMessage(message);
        return this;
    }

    public Scheduling build() {
        return scheduling;
    }

}
