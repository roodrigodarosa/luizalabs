package com.luizalabs.agendamento.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.luizalabs.agendamento.model.Scheduling;
import com.luizalabs.agendamento.service.exception.SchedulingBadRequestException;
import com.luizalabs.agendamento.service.exception.SchedulingNotFoundException;
import com.luizalabs.agendamento.repository.SchedulingRepository;

@Service
public class SchedulingService {

    private SchedulingRepository repository;

    public SchedulingService(SchedulingRepository repository) {
        this.repository = repository;
    }

    /**
     * Persiste um novo agendamento.
     * @param scheduling
     * @return
     * @throws SchedulingBadRequestException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Scheduling save(Scheduling scheduling) throws SchedulingBadRequestException {
        SchedulingValidator.validate(scheduling);
        return (Scheduling) repository.save(scheduling);
    }

    /**
     * Remover um determinado agendamento.
     * @param id
     * @throws SchedulingNotFoundException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(String id) throws SchedulingNotFoundException {
        Scheduling scheduling = this.findById(id);
        repository.delete(scheduling);
    }

    /**
     * Retorna um determinado agendamento.
     * @param id
     * @return
     * @throws SchedulingNotFoundException
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Scheduling findById(String id) throws SchedulingNotFoundException {
        return repository.findById(id).orElseThrow(() -> new SchedulingNotFoundException("Agendamento não encontrado: " + id));
    }

    /**
     * Retorna todos os agendamentos.
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Scheduling> findAll() {
        return (List<Scheduling>) repository.findAll();
    }

}
