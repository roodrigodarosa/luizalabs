package com.luizalabs.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizalabs.agendamento.model.Scheduling;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, String> {

}
