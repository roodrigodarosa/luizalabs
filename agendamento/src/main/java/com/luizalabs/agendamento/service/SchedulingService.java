package com.luizalabs.agendamento.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.luizalabs.agendamento.domain.Scheduling;
import com.luizalabs.agendamento.exception.SchedulingBadRequestException;
import com.luizalabs.agendamento.exception.SchedulingNotFoundException;
import com.luizalabs.agendamento.repository.SchedulingRepository;

@Service
public class SchedulingService {

    private SchedulingRepository repository;

    public SchedulingService(SchedulingRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Scheduling save(Scheduling scheduling) throws SchedulingBadRequestException {
        SchedulingValidator.validate(scheduling);
        return (Scheduling) repository.save(scheduling);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(String id) throws SchedulingNotFoundException {
        Scheduling scheduling = this.findById(id);
        repository.delete(scheduling);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Scheduling findById(String id) throws SchedulingNotFoundException {
        return repository.findById(id).orElseThrow(() -> new SchedulingNotFoundException("Agendamento não encontrado: " + id));
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Scheduling> findAll() {
        return (List<Scheduling>) repository.findAll();
    }

}
