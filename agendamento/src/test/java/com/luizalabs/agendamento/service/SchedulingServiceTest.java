package com.luizalabs.agendamento.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.luizalabs.agendamento.model.Scheduling;
import com.luizalabs.agendamento.model.enums.NotificationStatus;
import com.luizalabs.agendamento.model.enums.NotificationType;
import com.luizalabs.agendamento.repository.SchedulingRepository;
import com.luizalabs.agendamento.service.exception.SchedulingNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class SchedulingServiceTest {

    @InjectMocks
    private SchedulingService service;
    @Mock
    private SchedulingRepository repository;

    @Test
    public void whenSaveSchedulingThenReturnOk() throws Exception {
        Scheduling scheduling = buildScheduling();
        ArgumentCaptor<Scheduling> schedulingArgumentCaptor = ArgumentCaptor.forClass(Scheduling.class);

        service.save(scheduling);
        verify(repository).save(schedulingArgumentCaptor.capture());

        assertThat(schedulingArgumentCaptor.getValue()).satisfies(savedScheduling -> {
            assertThat(savedScheduling.getId().equals(scheduling.getId()));
            assertThat(savedScheduling.getScheduledDate().equals(scheduling.getScheduledDate()));
            assertThat(savedScheduling.getMessage().equals(scheduling.getMessage()));
            assertThat(savedScheduling.getRecipient().equals(scheduling.getRecipient()));
            assertThat(savedScheduling.getNotificationType().equals(scheduling.getNotificationType()));
            assertThat(savedScheduling.getStatus().equals(scheduling.getStatus()));
        });

    }

    @Test
    public void whenDeleteSchedulingThenReturnOk() throws Exception {
        Scheduling scheduling = buildScheduling();
        when(repository.findById(scheduling.getId())).thenReturn(Optional.of(scheduling));

        Assertions.assertThatCode(() -> service.delete(scheduling.getId())).doesNotThrowAnyException();

        verify(repository).findById(scheduling.getId());

    }

    @Test
    public void whenDeleteSchedulingNotFoundThenReturnException() throws Exception {
        Assertions.assertThatThrownBy(() -> service.delete("any-id"))
                .isInstanceOf(SchedulingNotFoundException.class)
                .hasMessage("Agendamento não encontrado: any-id");

    }

    @Test
    public void whenFindSchedulingByIdThenReturnOk() throws Exception {
        Scheduling scheduling = buildScheduling();
        when(repository.findById(scheduling.getId())).thenReturn(Optional.of(scheduling));

        assertThat(service.findById(scheduling.getId())).satisfies(findScheduling -> {
            assertThat(findScheduling.getId()).isEqualTo(scheduling.getId());
            assertThat(findScheduling.getScheduledDate()).isEqualTo(scheduling.getScheduledDate());
            assertThat(findScheduling.getMessage()).isEqualTo(scheduling.getMessage());
            assertThat(findScheduling.getRecipient()).isEqualTo(scheduling.getRecipient());
            assertThat(findScheduling.getNotificationType()).isEqualTo(scheduling.getNotificationType());
            assertThat(findScheduling.getStatus()).isEqualTo(scheduling.getStatus());
        });

        verify(repository).findById(scheduling.getId());

    }

    @Test
    public void whenFindAllSchedulingThenReturnOk() throws Exception {
        Scheduling scheduling = buildScheduling();
        Scheduling scheduling2 = new Scheduling()
                .setId("scheduling-id-2")
                .setScheduledDate(new Date(new Date().getTime() + 999999))
                .setMessage("Mensagem de agendamento-2")
                .setRecipient("47999560651")
                .setNotificationType(NotificationType.WHATSAPP)
                .setStatus(NotificationStatus.SENT);
        List<Scheduling> schedulingList = Arrays.asList(scheduling, scheduling2);

        when(repository.findAll()).thenReturn(schedulingList);

        assertThat(service.findAll()).satisfies(findScheduling -> {
            assertThat(findScheduling.get(0).getId()).isEqualTo(scheduling.getId());
            assertThat(findScheduling.get(0).getScheduledDate()).isEqualTo(scheduling.getScheduledDate());
            assertThat(findScheduling.get(0).getMessage()).isEqualTo(scheduling.getMessage());
            assertThat(findScheduling.get(0).getRecipient()).isEqualTo(scheduling.getRecipient());
            assertThat(findScheduling.get(0).getNotificationType()).isEqualTo(scheduling.getNotificationType());
            assertThat(findScheduling.get(0).getStatus()).isEqualTo(scheduling.getStatus());

            assertThat(findScheduling.get(1).getId()).isEqualTo(scheduling2.getId());
            assertThat(findScheduling.get(1).getScheduledDate()).isEqualTo(scheduling2.getScheduledDate());
            assertThat(findScheduling.get(1).getMessage()).isEqualTo(scheduling2.getMessage());
            assertThat(findScheduling.get(1).getRecipient()).isEqualTo(scheduling2.getRecipient());
            assertThat(findScheduling.get(1).getNotificationType()).isEqualTo(scheduling2.getNotificationType());
            assertThat(findScheduling.get(1).getStatus()).isEqualTo(scheduling2.getStatus());
        });

        verify(repository).findAll();

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