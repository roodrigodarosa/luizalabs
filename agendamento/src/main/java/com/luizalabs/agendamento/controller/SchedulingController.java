package com.luizalabs.agendamento.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.agendamento.controller.api.SchedulingRequest;
import com.luizalabs.agendamento.model.Scheduling;
import com.luizalabs.agendamento.service.SchedulingService;
import com.luizalabs.agendamento.service.exception.SchedulingBadRequestException;
import com.luizalabs.agendamento.service.exception.SchedulingNotFoundException;

@RestController
@RequestMapping({"/schedulings"})
public class SchedulingController extends AbstractController {

    private SchedulingService service;

    public SchedulingController(SchedulingService service) {
        this.service = service;
    }

    /**
     * Retorna todos os agendamentos,
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity findAll() {
        List<SchedulingRequest> listResponse = new ArrayList<>();
        service.findAll().forEach(a ->{
            listResponse.add(a.toRequest());
        });
        return buildOK(listResponse);
    }

    /**
     * Retorna um determinado agendamento.
     * @param id
     * @return
     */
    @GetMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity findById(@PathVariable String id) {
        try {
            Scheduling scheduling = service.findById(id);
            return buildOK(scheduling.toRequest());
        } catch (SchedulingNotFoundException e) {
            return buildError(e, e.getStatus());
        }
    }

    /**
     * Persiste um novo agendamento.
     * @param request
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody SchedulingRequest request)  {
        try {
            return buildCreated(service.save(request.toEntity()));
        } catch (SchedulingBadRequestException e) {
            return buildError(e, e.getStatus());
        }
    }


    /**
     * Remove um determinado agendamento.
     * @param id
     * @return
     */
    @DeleteMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity delete(@PathVariable String id) {
        try {
            service.delete(id);
            return buildNoContent();
        } catch (SchedulingNotFoundException e) {
            return buildError(e, e.getStatus());
        }
    }
}
