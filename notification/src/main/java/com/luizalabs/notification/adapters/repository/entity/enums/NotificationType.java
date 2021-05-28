package com.luizalabs.notification.adapters.repository.entity.enums;

import com.luizalabs.notification.usecases.exceptions.SchedulingBadRequestException;

public enum NotificationType {
    EMAIL,
    PUSH,
    SMS,
    WHATSAPP;

    public static NotificationType get(String value) throws SchedulingBadRequestException {
        try {
            return NotificationType.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new SchedulingBadRequestException("Tipo notificação inválida.");
        }
    }
}
