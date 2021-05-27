package com.luizalabs.agendamento.builder;

import java.util.Date;

import com.luizalabs.agendamento.controller.api.SchedulingRequest;

public class SchedulingRequestBuilder {

    private SchedulingRequest schedulingRequest;

    public SchedulingRequestBuilder() {
        schedulingRequest = new SchedulingRequest();
    }

    public static SchedulingRequestBuilder of() {
        return new SchedulingRequestBuilder();
    }

    public SchedulingRequestBuilder id(String id) {
        schedulingRequest.setId(id);
        return this;
    }

    public SchedulingRequestBuilder dateToSend(Date dateToSend) {
        schedulingRequest.setScheduledDate(dateToSend);
        return this;
    }

    public SchedulingRequestBuilder recipient(String recipient) {
        schedulingRequest.setRecipient(recipient);
        return this;
    }

    public SchedulingRequestBuilder recipientType(String recipientType) {
        schedulingRequest.setNotificationType(recipientType);
        return this;
    }
    public SchedulingRequestBuilder status(String status) {
        schedulingRequest.setStatus(status);
        return this;
    }

    public SchedulingRequestBuilder message(String message) {
        schedulingRequest.setMessage(message);
        return this;
    }

    public SchedulingRequest build() {
        return schedulingRequest;
    }

}
