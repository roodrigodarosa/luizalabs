package com.luizalabs.agendamento.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.Test;

import com.luizalabs.agendamento.domain.Scheduling;
import com.luizalabs.agendamento.enums.RecipientTypeEnum;
import com.luizalabs.agendamento.enums.StatusEnum;

public class SchedulingBuilderTest {

    @Test
    public void whenBuildSchedulingThenReturn() {
        Date date = new Date(new Date().getTime() + 999999);
        Scheduling scheduling = SchedulingBuilder.of().
                id("scheduling-id")
                .dateToSend(date)
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType(RecipientTypeEnum.EMAIL)
                .status(StatusEnum.NOT_SENT)
                .build();
        assertThat(scheduling.getId()).isEqualTo("scheduling-id");
        assertThat(scheduling.getDateToSend()).isEqualTo(date);
        assertThat(scheduling.getMessage()).isEqualTo("Mensagem de agendamento");
        assertThat(scheduling.getRecipient()).isEqualTo("teste@luizalabs.com.br");
        assertThat(scheduling.getRecipientType()).isEqualTo(RecipientTypeEnum.EMAIL);
        assertThat(scheduling.getStatus()).isEqualTo(StatusEnum.NOT_SENT);
    }
}