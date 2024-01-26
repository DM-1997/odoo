package com.sintaxy.oodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sintaxy.oodo.model.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, Long> {

}
