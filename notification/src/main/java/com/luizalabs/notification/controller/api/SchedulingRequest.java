package com.luizalabs.notification.controller.api;

import java.util.Date;

import com.luizalabs.notification.model.Scheduling;
import com.luizalabs.notification.model.enums.NotificationStatus;
import com.luizalabs.notification.model.enums.NotificationType;

public class SchedulingRequest {

    private String id;
    private Date scheduledDate;
    private String recipient;
    private String notificationType;
    private String status = "SCHEDULED";
    private String message;

    public String getId() {
        return id;
    }

    public SchedulingRequest setId(String id) {
        this.id = id;
        return this;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public SchedulingRequest setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
        return this;
    }

    public String getRecipient() {
        return recipient;
    }

    public SchedulingRequest setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public SchedulingRequest setNotificationType(String notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public SchedulingRequest setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SchedulingRequest setMessage(String message) {
        this.message = message;
        return this;
    }

    public Scheduling toEntity(){
        return new Scheduling()
                .setId(getId())
                .setScheduledDate(getScheduledDate())
                .setNotificationType(NotificationType.valueOf(getNotificationType()))
                .setRecipient(getRecipient())
                .setMessage(getMessage())
                .setStatus(NotificationStatus.valueOf(getStatus()));
    }

}
