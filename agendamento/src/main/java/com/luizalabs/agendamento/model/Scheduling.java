package com.luizalabs.agendamento.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.luizalabs.agendamento.model.enums.NotificationType;
import com.luizalabs.agendamento.model.enums.NotificationStatus;

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

    public void setId(String id) {
        this.id = id;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date dateToSend) {
        this.scheduledDate = dateToSend;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType recipientType) {
        this.notificationType = recipientType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Scheduling{" +
                "id='" + id + '\'' +
                ", dateToSend=" + scheduledDate +
                ", recipient='" + recipient + '\'' +
                ", recipientType=" + notificationType +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Scheduling)) {
            return false;
        }
        Scheduling that = (Scheduling) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getScheduledDate(), that.getScheduledDate()) &&
                Objects.equals(getRecipient(), that.getRecipient()) &&
                getNotificationType() == that.getNotificationType() &&
                Objects.equals(getMessage(), that.getMessage()) &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getScheduledDate(), getRecipient(), getNotificationType(), getMessage(), getStatus());
    }
}
