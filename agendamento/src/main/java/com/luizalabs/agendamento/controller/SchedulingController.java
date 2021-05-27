package com.luizalabs.agendamento.controller;

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

import com.luizalabs.agendamento.controller.dto.SchedulingDTO;
import com.luizalabs.agendamento.converter.SchedulingConverter;
import com.luizalabs.agendamento.domain.Scheduling;
import com.luizalabs.agendamento.exception.SchedulingBadRequestException;
import com.luizalabs.agendamento.exception.SchedulingNotFoundException;
import com.luizalabs.agendamento.service.SchedulingService;

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
        List<Scheduling> schedulingList = service.findAll();
        return buildOK(SchedulingConverter.entityListToDtoList(schedulingList));
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
            return buildOK(SchedulingConverter.entityToDto(scheduling));
        } catch (SchedulingNotFoundException e) {
            return buildError(e, e.getStatus());
        }
    }

    /**
     * Persiste um novo agendamento.
     * @param dto
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody SchedulingDTO dto)  {
        try {
            Scheduling scheduling = SchedulingConverter.dtoToEntity(dto);
            return buildCreated(service.save(scheduling));
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
