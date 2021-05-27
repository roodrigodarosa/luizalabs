package com.luizalabs.agendamento.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.luizalabs.agendamento.model.Scheduling;
import com.luizalabs.agendamento.model.enums.NotificationType;
import com.luizalabs.agendamento.model.enums.NotificationStatus;

public class SchedulingBuilderTest {

    @Test
    public void whenBuildSchedulingThenReturn() {
        Date date = new Date(new Date().getTime() + 999999);
        Scheduling scheduling = SchedulingBuilder.of().
                id("scheduling-id")
                .dateToSend(date)
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType(NotificationType.EMAIL)
                .status(NotificationStatus.NOT_SENT)
                .build();
        assertThat(scheduling.getId()).isEqualTo("scheduling-id");
        assertThat(scheduling.getScheduledDate()).isEqualTo(date);
        assertThat(scheduling.getMessage()).isEqualTo("Mensagem de agendamento");
        assertThat(scheduling.getRecipient()).isEqualTo("teste@luizalabs.com.br");
        assertThat(scheduling.getNotificationType()).isEqualTo(NotificationType.EMAIL);
        assertThat(scheduling.getStatus()).isEqualTo(NotificationStatus.NOT_SENT);
    }
}