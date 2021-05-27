package com.luizalabs.agendamento.controller.api.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.luizalabs.agendamento.builder.SchedulingBuilder;
import com.luizalabs.agendamento.builder.SchedulingRequestBuilder;
import com.luizalabs.agendamento.controller.api.SchedulingRequest;
import com.luizalabs.agendamento.model.Scheduling;
import com.luizalabs.agendamento.model.enums.NotificationType;
import com.luizalabs.agendamento.model.enums.NotificationStatus;
import com.luizalabs.agendamento.service.exception.SchedulingBadRequestException;

public class SchedulingConverterTest {

    @Test
    public void whenConvertDtoToEntityThenReturn() throws Exception {
        SchedulingRequest dto = SchedulingRequestBuilder.of().
                id("scheduling-id")
                .dateToSend(new Date())
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType("EMAIL")
                .status("NOT_SENT")
                .build();
        assertThat(SchedulingConverter.requestToEntity(dto)).satisfies(scheduling -> {
            assertThat(scheduling.getId()).isEqualTo(scheduling.getId());
            assertThat(scheduling.getScheduledDate()).isEqualTo(dto.getScheduledDate());
            assertThat(scheduling.getMessage()).isEqualTo(dto.getMessage());
            assertThat(scheduling.getRecipient()).isEqualTo(dto.getRecipient());
            assertThat(scheduling.getNotificationType().name()).isEqualTo(dto.getNotificationType());
            assertThat(scheduling.getStatus().name()).isEqualTo(dto.getStatus());
        });
    }

    @Test
    public void whenConvertEntityToDtoThenReturn() throws Exception {
        Scheduling scheduling = SchedulingBuilder.of().
                id("scheduling-id")
                .dateToSend(new Date())
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType(NotificationType.EMAIL)
                .status(NotificationStatus.NOT_SENT)
                .build();
        assertThat(SchedulingConverter.entityToRequest(scheduling)).satisfies(dto -> {
            assertThat(dto.getId()).isEqualTo(scheduling.getId());
            assertThat(dto.getScheduledDate()).isEqualTo(scheduling.getScheduledDate());
            assertThat(dto.getMessage()).isEqualTo(scheduling.getMessage());
            assertThat(dto.getRecipient()).isEqualTo(scheduling.getRecipient());
            assertThat(dto.getNotificationType()).isEqualTo(scheduling.getNotificationType().name());
            assertThat(dto.getStatus()).isEqualTo(scheduling.getStatus().name());
        });
    }

    @Test
    public void whenConvertDtoToEntityWithInvalidRecipientTypeThenException() throws Exception {
        SchedulingRequest dto = SchedulingRequestBuilder.of().
                id("scheduling-id")
                .dateToSend(new Date())
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType("INVALID_TYPE")
                .status("NOT_SENT")
                .build();

        Assertions.assertThatThrownBy(() ->  SchedulingConverter.requestToEntity(dto))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Tipo de destinatário inválido: INVALID_TYPE");

    }

    @Test
    public void whenConvertDtoToEntityWithInvalidStatusThenException() throws Exception {
        SchedulingRequest dto = SchedulingRequestBuilder.of().
                id("scheduling-id")
                .dateToSend(new Date())
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType("EMAIL")
                .status("INVALID_STATUS")
                .build();

        Assertions.assertThatThrownBy(() ->  SchedulingConverter.requestToEntity(dto))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Status inválido: INVALID_STATUS");

    }

}