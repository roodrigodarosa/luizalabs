package com.luizalabs.agendamento.controller.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.luizalabs.agendamento.builder.SchedulingBuilder;
import com.luizalabs.agendamento.builder.SchedulingRequestBuilder;
import com.luizalabs.agendamento.controller.api.SchedulingRequest;
import com.luizalabs.agendamento.model.Scheduling;
import com.luizalabs.agendamento.model.enums.NotificationType;
import com.luizalabs.agendamento.model.enums.NotificationStatus;
import com.luizalabs.agendamento.service.exception.SchedulingBadRequestException;

public class SchedulingConverter {

    public static Scheduling requestToEntity(SchedulingRequest dto) throws SchedulingBadRequestException {
        return SchedulingBuilder.of()
                .id(dto.getId())
                .dateToSend(dto.getScheduledDate())
                .recipient(dto.getRecipient())
                .recipientType(converterRecipientType(dto.getNotificationType()))
                .status(converterStatus(dto.getStatus()))
                .message(dto.getMessage())
                .build();
    }

    public static SchedulingRequest entityToRequest(Scheduling scheduling) {
        return SchedulingRequestBuilder.of()
                .id(scheduling.getId())
                .dateToSend(scheduling.getScheduledDate())
                .recipient(scheduling.getRecipient())
                .recipientType(scheduling.getNotificationType() != null ? scheduling.getNotificationType().name() : null)
                .status(scheduling.getStatus() != null ? scheduling.getStatus().name() : null)
                .message(scheduling.getMessage())
                .build();
    }

    public static List<SchedulingRequest> entityListToRequestList(List<Scheduling> schedulingList) {
        List<SchedulingRequest> listDTO = new ArrayList<>();
        if (schedulingList != null) {
            schedulingList.forEach(scheduling ->
                    listDTO.add(entityToRequest(scheduling)));
        }
        return listDTO;
    }


    private static NotificationType converterRecipientType(String type) throws SchedulingBadRequestException {
        if(type == null){
            return null;
        }
        try {
            return NotificationType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SchedulingBadRequestException("Tipo de destinatário inválido: " + type);
        }
    }
    private static NotificationStatus converterStatus(String status) throws SchedulingBadRequestException {

        if(status == null){
            return null;
        }
        try {
            return NotificationStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SchedulingBadRequestException("Status inválido: " + status);
        }
    }
}
