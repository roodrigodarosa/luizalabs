package com.luizalabs.notification.service;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.luizalabs.notification.adapters.repository.entity.Scheduling;
import com.luizalabs.notification.adapters.repository.entity.enums.NotificationStatus;
import com.luizalabs.notification.adapters.repository.entity.enums.NotificationType;
import com.luizalabs.notification.usecases.exceptions.SchedulingBadRequestException;
import com.luizalabs.notification.usecases.SchedulingValidator;

public class SchedulingValidatorTest {


    @Test
    public void whenValidateSchedulingWithoutErrosThenOk() throws Exception {
        Scheduling scheduling = buildScheduling();
        Assertions.assertThatCode(() -> SchedulingValidator.validate(scheduling)).doesNotThrowAnyException();
    }

    @Test
    public void whenValidateSchedulingWithNullEntityThenReturnException() {
        Assertions.assertThatThrownBy(() -> SchedulingValidator.validate(null))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Scheduling está nulo.");
    }

    @Test
    public void whenValidateSchedulingWithNullDateToSendThenReturnException() {
        Scheduling scheduling = buildScheduling();
        scheduling.setScheduledDate(null);
        Assertions.assertThatThrownBy(() -> SchedulingValidator.validate(scheduling))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Data de agendamento está nulo.");
    }

    @Test
    public void whenValidateSchedulingWithNullRecipientThenReturnException() {
        Scheduling scheduling = buildScheduling();
        scheduling.setRecipient(null);
        Assertions.assertThatThrownBy(() -> SchedulingValidator.validate(scheduling))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Destinatário está nulo ou vazio.");
    }

    @Test
    public void whenValidateSchedulingWithEmptyRecipientThenReturnException() {
        Scheduling scheduling = buildScheduling();
        scheduling.setRecipient("");
        Assertions.assertThatThrownBy(() -> SchedulingValidator.validate(scheduling))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Destinatário está nulo ou vazio.");
    }

    @Test
    public void whenValidateSchedulingWithNullMessageThenReturnException() {
        Scheduling scheduling = buildScheduling();
        scheduling.setMessage(null);
        Assertions.assertThatThrownBy(() -> SchedulingValidator.validate(scheduling))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Mensagem está nula ou vazia.");
    }

    @Test
    public void whenValidateSchedulingWithEmptyMessageThenReturnException() {
        Scheduling scheduling = buildScheduling();
        scheduling.setMessage("");

        Assertions.assertThatThrownBy(() -> SchedulingValidator.validate(scheduling))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Mensagem está nula ou vazia.");
    }

    @Test
    public void whenValidateSchedulingWithNullRecepientTypeThenReturnException() {
        Scheduling scheduling = buildScheduling();
        scheduling.setNotificationType(null);
        Assertions.assertThatThrownBy(() -> SchedulingValidator.validate(scheduling))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Tipo de destinatário está nulo.");

    }

    @Test
    public void whenValidateSchedulingWithNullStatusThenReturnException() {
        Scheduling scheduling = buildScheduling();
        scheduling.setStatus(null);

        Assertions.assertThatThrownBy(() -> SchedulingValidator.validate(scheduling))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Status do agendamento está nulo.");
    }

    @Test
    public void whenValidateSchedulingWithDateToSendBeforeNowThenReturnException() {
        Scheduling scheduling = buildScheduling();
        scheduling.setScheduledDate(new Date(new Date().getTime() - 99999));

        Assertions.assertThatThrownBy(() -> SchedulingValidator.validate(scheduling))
                .isInstanceOf(SchedulingBadRequestException.class)
                .hasMessage("Data de envio do agendamento deve ser maior que a data atual.");

    }

    private Scheduling buildScheduling() {
        return new Scheduling().
                setId("scheduling-id")
                .setScheduledDate(new Date(new Date().getTime() + 999999))
                .setMessage("Mensagem de agendamento")
                .setRecipient("teste@luizalabs.com.br")
                .setNotificationType(NotificationType.EMAIL)
                .setStatus(NotificationStatus.NOT_SENT);
    }
}