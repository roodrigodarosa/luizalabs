package com.luizalabs.notification.adapters.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.luizalabs.notification.adapters.repository.entity.Scheduling;
import com.luizalabs.notification.adapters.repository.entity.NotificationStatus;
import com.luizalabs.notification.adapters.repository.entity.NotificationType;
import com.luizalabs.notification.usecases.SchedulingService;
import com.luizalabs.notification.usecases.exceptions.SchedulingBadRequestException;
import com.luizalabs.notification.usecases.exceptions.SchedulingNotFoundException;

@RunWith(SpringRunner.class)
@WebMvcTest(SchedulingController.class)
public class SchedulingControllerTest {

    @MockBean
    private SchedulingService service;

    @Autowired
    private MockMvc mock;

    @Test
    public void whenFindAllThenReturnOK() throws Exception {
        List<Scheduling> result = new ArrayList<>();
        result.add(buildScheduling());
        when(service.findAll()).thenReturn(result);
        mock.perform(get("/schedulings")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value())).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("scheduling-id")).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].scheduledDate").isNotEmpty()).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].message").value("Mensagem de agendamento")).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].recipient").value("teste@luizalabs.com.br")).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].notificationType").value("EMAIL")).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("SENT_WITH_ERROR"));
        verify(service).findAll();
    }

    @Test
    public void whenFindByIdThenReturnOK() throws Exception {
        Scheduling scheduling = buildScheduling();
        when(service.findById("scheduling-id")).thenReturn(scheduling);
        mock.perform(get("/schedulings/scheduling-id")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value())).
                andExpect(MockMvcResultMatchers.jsonPath("$.id").value("scheduling-id")).
                andExpect(MockMvcResultMatchers.jsonPath("$.scheduledDate").isNotEmpty()).
                andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Mensagem de agendamento")).
                andExpect(MockMvcResultMatchers.jsonPath("$.recipient").value("teste@luizalabs.com.br")).
                andExpect(MockMvcResultMatchers.jsonPath("$.notificationType").value("EMAIL")).
                andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SENT_WITH_ERROR"));
        verify(service).findById("scheduling-id");
    }

    @Test
    public void whenFindAllWithExceptionThenReturnError() throws Exception {
        List<Scheduling> result = new ArrayList<>();
        result.add(buildScheduling());
        when(service.findById("error-id")).thenThrow(new SchedulingNotFoundException("Error"));
        mock.perform(get("/schedulings/error-id")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
        verify(service).findById("error-id");
    }

    @Test
    public void whenDeleteByIdThenReturnNoContent() throws Exception {
        Scheduling scheduling = buildScheduling();
        mock.perform(delete("/schedulings/scheduling-id")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    public void whenDeleteByIdWithErrorThenRetunException() throws Exception {
        doThrow(new SchedulingNotFoundException("Error")).when(service).delete("error-id");
        mock.perform(delete("/schedulings/error-id")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void whenCreateThenReturnCreated() throws Exception {
        String json ="   {\n"
                + "        \"scheduledDate\": \"2021-08-21T18:25:43-05:00\",\n"
                + "        \"recipient\": \"teste@luizalabs.com.br\",\n"
                + "        \"status\": \"SENT_WITH_ERROR\",\n"
                + "        \"notificationType\": \"EMAIL\",\n"
                + "        \"message\": \"Teste de agendamento\"\n"
                + "    }";
        Scheduling scheduling = buildScheduling();
        when(service.save(any(Scheduling.class))).thenReturn(scheduling);
        mock.perform(post("/schedulings").content(json).contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value())).
                andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty()).
                andExpect(MockMvcResultMatchers.jsonPath("$.scheduledDate").isNotEmpty()).
                andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Mensagem de agendamento")).
                andExpect(MockMvcResultMatchers.jsonPath("$.notificationType").value("EMAIL")).
                andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SENT_WITH_ERROR"));
        verify(service).save(any(Scheduling.class));
    }

    @Test
    public void whenCreateThenWithErrorThenReturnError() throws Exception {
        String json ="   {\n"
                + "        \"scheduledDate\": \"2021-08-21T18:25:43-05:00\",\n"
                + "        \"recipient\": \"teste@luizalabs.com.br\",\n"
                + "        \"notificationType\": \"NOTIFICATION-TYPE-ERRADO\",\n"
                + "        \"message\": \"Teste de agendamento\"\n"
                + "    }";
        when(service.save(any(Scheduling.class))).thenThrow(new SchedulingBadRequestException("Error"));
        mock.perform(post("/schedulings").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value())).
                andExpect(MockMvcResultMatchers.jsonPath("$").value("Tipo notificação inválida."));
    }

    private Scheduling buildScheduling() {
        return new Scheduling().
                setId("scheduling-id")
                .setScheduledDate(new Date(new Date().getTime() + 999999))
                .setMessage("Mensagem de agendamento")
                .setRecipient("teste@luizalabs.com.br")
                .setNotificationType(NotificationType.EMAIL)
                .setStatus(NotificationStatus.SENT_WITH_ERROR);
    }

}