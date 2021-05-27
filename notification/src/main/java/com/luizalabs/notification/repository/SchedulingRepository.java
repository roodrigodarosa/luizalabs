package com.luizalabs.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizalabs.notification.model.Scheduling;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, String> {

}
