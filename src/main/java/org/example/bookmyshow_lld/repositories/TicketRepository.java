package org.example.bookmyshow_lld.repositories;

import org.example.bookmyshow_lld.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
