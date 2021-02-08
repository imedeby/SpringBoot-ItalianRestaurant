package ds.example.dsproject.implementations;



import ds.example.dsproject.dto.MetDTO;
import ds.example.dsproject.dto.TicketDTO;
import ds.example.dsproject.entities.Client;
import ds.example.dsproject.entities.Met;
import ds.example.dsproject.entities.TableIT;
import ds.example.dsproject.entities.Ticket;
import ds.example.dsproject.repositories.ClientRepository;
import ds.example.dsproject.repositories.TableRepository;
import ds.example.dsproject.repositories.TicketRepository;
import ds.example.dsproject.services.MetService;
import ds.example.dsproject.services.TicketService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImp implements TicketService {

    private TicketRepository ticketRepository;
    private ClientRepository clientRepository;
    private TableRepository tableRepository;
    private MetService metService;

    private ModelMapper mapper;


    @Override
    public List<TicketDTO> getTickets() {
        List<TicketDTO> liste = ticketRepository.findAll().stream().map(t -> mapper.map(t,TicketDTO.class)).collect(Collectors.toList());
        return liste;
    }


    @Override
    public TicketDTO addTicket(TicketDTO ticketDTO, Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        TableIT table = tableRepository.findById(ticketDTO.getTableIT().getId()).orElseThrow(() -> new EntityNotFoundException("Table not found"));
        List<MetDTO> listMetDto = ticketDTO.getMets();
        List<Met> lis = listMetDto.stream().map((m) -> metService.findMet(metService.metMapper(m)))
                .collect(Collectors.toList());
        ticketDTO.setMets(null);
        Ticket ticket = mapper.map(ticketDTO, Ticket.class);
        ticket.setClient(client);
        ticket.setTableIT(table);
        ticket.setMets(lis);
        ticket = ticketRepository.save(ticket);
        ticketDTO = mapper.map(ticket, TicketDTO.class);

        return ticketDTO;
    }

    @Override
    public TicketDTO updateTicket(TicketDTO ticketDTO){
        Optional<Ticket> opt = Optional.ofNullable(ticketRepository.findById(ticketDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Ticket not found")));
        Ticket ticket = opt.get();
        List<Met> m = new ArrayList<>();
        if(opt.isPresent()){
            ticket.setNumber(ticketDTO.getNumber());
            ticket.setNbCouvert(ticketDTO.getNbCouvert());
            ticket.setAddition(ticketDTO.getAddition());
            ticket.setDate(ticketDTO.getDate());
            for (MetDTO met : ticketDTO.getMets()){
                 m.add(mapper.map(met, Met.class));
            }
            ticket.setMets(m);
            ticket.setTableIT(mapper.map(ticketDTO.getTableIT(),TableIT.class));
            ticketRepository.save(ticket);
        }
        return mapper.map(ticket, TicketDTO.class);
    }

    @Override
    public List<TicketDTO> deleteTicket(Long id){
        Optional<Ticket> opt = Optional.ofNullable(ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found")));
        ticketRepository.deleteById(opt.get().getId());
        return this.getTickets();
    }
    @Override
    public Double getIncome(LocalDate d,String s){
        Double income =0.0;
        Period p;
        if (s.equalsIgnoreCase("day")) {
            p = Period.ofDays(1);
        }
        else if (s.equalsIgnoreCase("week")) {
            p = Period.ofWeeks(1);
        }
        else if (s.equalsIgnoreCase("month")){
            p = Period.ofMonths(1);
        }
        else {
            p = Period.ZERO;
        }
        List<Ticket> tickets = ticketRepository.findAll().stream()
                .filter(t -> (t.getDate().isAfter(d) || t.getDate().isEqual(d)) && t.getDate().isBefore(d.plus(p)))
                .collect(Collectors.toList());

        for(Ticket ticket : tickets){
            for(Met met : ticket.getMets()){
                income = income + met.getPrice();
            }
            income = income + ticket.getAddition() + ticket.getTableIT().getSupplement();
        }
        return income;
    }

    @Override
    public Double getIncomePerPeriod (LocalDate d1 , LocalDate d2){
        List<Ticket> tickets = ticketRepository.findAll().stream()
                .filter(t -> (t.getDate().isAfter(d1)  || t.getDate().equals(d1)) && (t.getDate().isBefore(d2) || t.getDate().equals(d2)))
                .collect(Collectors.toList());
        Double income =0.0;
        for(Ticket ticket : tickets){
            for(Met met : ticket.getMets()){
                income = income + met.getPrice();
            }
            income = income + ticket.getAddition() + ticket.getTableIT().getSupplement();
        }
        return income;
    }

}
