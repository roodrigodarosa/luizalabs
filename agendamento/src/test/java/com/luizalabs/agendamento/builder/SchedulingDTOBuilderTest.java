package com.luizalabs.agendamento.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.luizalabs.agendamento.controller.dto.SchedulingDTO;
import com.luizalabs.agendamento.enums.RecipientTypeEnum;
import com.luizalabs.agendamento.enums.StatusEnum;

public class SchedulingDTOBuilderTest {

    @Test
    public void whenBuildSchedulingDtoThenReturn() {
        Date date = new Date(new Date().getTime() + 999999);
        SchedulingDTO schedulingDTO = SchedulingDTOBuilder.of().
                id("scheduling-id")
                .dateToSend(date)
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType("EMAIL")
                .status("NOT_SENT")
                .build();
        assertThat(schedulingDTO.getId()).isEqualTo("scheduling-id");
        assertThat(schedulingDTO.getDateToSend()).isEqualTo(date);
        assertThat(schedulingDTO.getMessage()).isEqualTo("Mensagem de agendamento");
        assertThat(schedulingDTO.getRecipient()).isEqualTo("teste@luizalabs.com.br");
        assertThat(schedulingDTO.getRecipientType()).isEqualTo(RecipientTypeEnum.EMAIL.name());
        assertThat(schedulingDTO.getStatus()).isEqualTo(StatusEnum.NOT_SENT.name());
    }

}