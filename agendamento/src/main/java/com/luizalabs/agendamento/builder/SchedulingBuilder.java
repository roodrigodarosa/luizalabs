package com.luizalabs.agendamento.builder;

import java.util.Date;

import com.luizalabs.agendamento.model.Scheduling;
import com.luizalabs.agendamento.model.enums.NotificationType;
import com.luizalabs.agendamento.model.enums.NotificationStatus;

public class SchedulingBuilder {

    private Scheduling scheduling;

    public SchedulingBuilder() {
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
        scheduling.setScheduledDate(dateToSend);
        return this;
    }

    public SchedulingBuilder recipient(String recipient) {
        scheduling.setRecipient(recipient);
        return this;
    }

    public SchedulingBuilder recipientType(NotificationType notificationType) {
        scheduling.setNotificationType(notificationType);
        return this;
    }

    public SchedulingBuilder status(NotificationStatus notificationStatus) {
        scheduling.setStatus(notificationStatus);
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
