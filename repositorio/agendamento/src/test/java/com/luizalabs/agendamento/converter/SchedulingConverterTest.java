package com.luizalabs.agendamento.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.luizalabs.agendamento.builder.SchedulingBuilder;
import com.luizalabs.agendamento.builder.SchedulingDTOBuilder;
import com.luizalabs.agendamento.controller.dto.SchedulingDTO;
import com.luizalabs.agendamento.domain.Scheduling;
import com.luizalabs.agendamento.enums.RecipientTypeEnum;
import com.luizalabs.agendamento.enums.StatusEnum;
import com.luizalabs.agendamento.exception.SchedulingBadRequestException;

public class SchedulingConverterTest {

    @Test
    public void whenConvertDtoToEntityThenReturn() throws Exception {
        SchedulingDTO dto = SchedulingDTOBuilder.of().
                id("scheduling-id")
                .dateToSend(new Date())
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType("EMAIL")
                .status("NOT_SENT")
                .build();
        assertThat(SchedulingConverter.dtoToEntity(dto)).satisfies(scheduling -> {
            assertThat(scheduling.getId()).isEqualTo(scheduling.getId());
            assertThat(scheduling.getDateToSend()).isEqualTo(dto.getDateToSend());
            assertThat(scheduling.getMessage()).isEqualTo(dto.getMessage());
            assertThat(scheduling.getRecipient()).isEqualTo(dto.getRecipient());
            assertThat(scheduling.getRecipientType().name()).isEqualTo(dto.getRecipientType());
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
                .recipientType(RecipientTypeEnum.EMAIL)
                .status(StatusEnum.NOT_SENT)
                .build();
        assertThat(SchedulingConverter.entityToDto(scheduling)).satisfies(dto -> {
            assertThat(dto.getId()).isEqualTo(scheduling.getId());
            assertThat(dto.getDateToSend()).isEqualTo(scheduling.getDateToSend());
            assertThat(dto.getMessage()).isEqualTo(scheduling.getMessage());
            assertThat(dto.getRecipient()).isEqualTo(scheduling.getRecipient());
            assertThat(dto.getRecipientType()).isEqualTo(scheduling.getRecipientType().name());
            assertThat(dto.getStatus()).isEqualTo(scheduling.getStatus().name());
        });
    }

    @Test
    public void whenConvertDtoToEntityWithInvalidRecipientTypeThenException() throws Exception {
        SchedulingDTO dto = SchedulingDTOBuilder.of().
                id("scheduling-id")
                .dateToSend(new Date())
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType("INVALID_TYPE")
                .status("NOT_SENT")
                .build();

        Assertions.assertThatThrownBy(() ->  SchedulingConverter.dtoToEntity(dto))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Tipo de destinatário inválido: INVALID_TYPE");

    }

    @Test
    public void whenConvertDtoToEntityWithInvalidStatusThenException() throws Exception {
        SchedulingDTO dto = SchedulingDTOBuilder.of().
                id("scheduling-id")
                .dateToSend(new Date())
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType("EMAIL")
                .status("INVALID_STATUS")
                .build();

        Assertions.assertThatThrownBy(() ->  SchedulingConverter.dtoToEntity(dto))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Status inválido: INVALID_STATUS");

    }

}