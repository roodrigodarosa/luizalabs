package com.luizalabs.notification.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.luizalabs.notification.controller.api.SchedulingRequest;
import com.luizalabs.notification.model.enums.NotificationStatus;
import com.luizalabs.notification.model.enums.NotificationType;

@Entity
@Table(name = "scheduling")
public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID", length = 36, nullable = false, updatable = false)
    private String id;

    @Column(name = "SCHEDULED_DATE", nullable = false)
    private Date scheduledDate;

    @Column(name = "RECIPIENT", nullable = false)
    private String recipient;

    @Enumerated(EnumType.STRING)
    @Column(name = "NOTIFICATION_TYPE", nullable = false)
    private NotificationType notificationType;

    @Column(name = "MESSAGE", length = 1000, nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private NotificationStatus status;

    public String getId() {
        return id;
    }

    public Scheduling setId(String id) {
        this.id = id;
        return this;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public Scheduling setScheduledDate(Date dateToSend) {
        this.scheduledDate = dateToSend;
        return this;
    }

    public String getRecipient() {
        return recipient;
    }

    public Scheduling setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public Scheduling setNotificationType(NotificationType recipientType) {
        this.notificationType = recipientType;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Scheduling setMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public Scheduling setStatus(NotificationStatus status) {
        this.status = status;
        return this;
    }

    public SchedulingRequest toRequest(){
        return new SchedulingRequest()
                .setId(getId())
                .setScheduledDate(getScheduledDate())
                .setNotificationType(getNotificationType().name())
                .setRecipient(getRecipient())
                .setMessage(getMessage())
                .setStatus(getStatus().name());
    }

}
