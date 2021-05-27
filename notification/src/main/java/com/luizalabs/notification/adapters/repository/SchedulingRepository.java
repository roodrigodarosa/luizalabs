package com.luizalabs.notification.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizalabs.notification.adapters.repository.entity.Scheduling;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, String> {

}
