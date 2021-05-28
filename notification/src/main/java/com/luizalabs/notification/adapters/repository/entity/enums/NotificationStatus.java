package com.luizalabs.notification.adapters.repository.entity.enums;

import com.luizalabs.notification.usecases.exceptions.SchedulingBadRequestException;

public enum NotificationStatus {
    SCHEDULED,
    SENT,
    SENT_WITH_ERROR;

    public static NotificationStatus get(String value) throws SchedulingBadRequestException {
        try {
            return NotificationStatus.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new SchedulingBadRequestException("Status de agendamento inválido.");
        }
    }

}
