package com.luizalabs.agendamento.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.luizalabs.agendamento.controller.api.SchedulingRequest;
import com.luizalabs.agendamento.model.enums.NotificationType;
import com.luizalabs.agendamento.model.enums.NotificationStatus;

public class SchedulingRequestBuilderTest {

    @Test
    public void whenBuildSchedulingDtoThenReturn() {
        Date date = new Date(new Date().getTime() + 999999);
        SchedulingRequest schedulingRequest = SchedulingRequestBuilder.of().
                id("scheduling-id")
                .dateToSend(date)
                .message("Mensagem de agendamento")
                .recipient("teste@luizalabs.com.br")
                .recipientType("EMAIL")
                .status("NOT_SENT")
                .build();
        assertThat(schedulingRequest.getId()).isEqualTo("scheduling-id");
        assertThat(schedulingRequest.getScheduledDate()).isEqualTo(date);
        assertThat(schedulingRequest.getMessage()).isEqualTo("Mensagem de agendamento");
        assertThat(schedulingRequest.getRecipient()).isEqualTo("teste@luizalabs.com.br");
        assertThat(schedulingRequest.getNotificationType()).isEqualTo(NotificationType.EMAIL.name());
        assertThat(schedulingRequest.getStatus()).isEqualTo(NotificationStatus.NOT_SENT.name());
    }

}