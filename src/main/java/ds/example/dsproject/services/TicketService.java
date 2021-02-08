package ds.example.dsproject.services;


import ds.example.dsproject.dto.TicketDTO;


import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    List<TicketDTO> getTickets();
    TicketDTO addTicket(TicketDTO ticketDTO,Long id);
    List<TicketDTO> deleteTicket(Long id);
    TicketDTO updateTicket(TicketDTO ticketDTO);
    Double getIncome(LocalDate d,String s);
    Double getIncomePerPeriod (LocalDate d1 , LocalDate d2);

}
