package ds.example.dsproject.repositories;

import ds.example.dsproject.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
