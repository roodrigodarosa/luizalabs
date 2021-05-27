package com.luizalabs.agendamento.domain;

import static com.luizalabs.agendamento.enums.StatusEnum.NOT_SENT;

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

import com.luizalabs.agendamento.enums.RecipientTypeEnum;
import com.luizalabs.agendamento.enums.StatusEnum;

@Entity
@Table(name = "scheduling")
public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID", length = 36, nullable = false, updatable = false)
    private String id;

    @Column(name = "DATE_TO_SEND", nullable = false)
    private Date dateToSend;

    @Column(name = "RECIPIENT", nullable = false)
    private String recipient;

    @Enumerated(EnumType.STRING)
    @Column(name = "RECIPIENT_TYPE", nullable = false)
    private RecipientTypeEnum recipientType;

    @Column(name = "MESSAGE", length = 1000, nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private StatusEnum status = NOT_SENT;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateToSend() {
        return dateToSend;
    }

    public void setDateToSend(Date dateToSend) {
        this.dateToSend = dateToSend;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public RecipientTypeEnum getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(RecipientTypeEnum recipientType) {
        this.recipientType = recipientType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Scheduling{" +
                "id='" + id + '\'' +
                ", dateToSend=" + dateToSend +
                ", recipient='" + recipient + '\'' +
                ", recipientType=" + recipientType +
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
                Objects.equals(getDateToSend(), that.getDateToSend()) &&
                Objects.equals(getRecipient(), that.getRecipient()) &&
                getRecipientType() == that.getRecipientType() &&
                Objects.equals(getMessage(), that.getMessage()) &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateToSend(), getRecipient(), getRecipientType(), getMessage(), getStatus());
    }
}
