package ds.example.dsproject.implementations;

import ds.example.dsproject.dto.ClientDTO;
import ds.example.dsproject.entities.Client;
import ds.example.dsproject.repositories.ClientRepository;
import ds.example.dsproject.repositories.TicketRepository;
import ds.example.dsproject.services.ClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImp implements ClientService {

    private ClientRepository clientRepository;
    private TicketRepository ticketRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private ModelMapper mapper;

    @Override
    public List<ClientDTO> getClients(){

        List<ClientDTO> liste = clientRepository.findAll().stream().map(c -> mapper.map(c,ClientDTO.class)).collect(Collectors.toList());
        return liste;
    }
    @Override
    public ClientDTO addClient(ClientDTO clientDTO){
        Client client = mapper.map(clientDTO, Client.class);
        client = clientRepository.save(client);
        clientDTO = mapper.map(client, ClientDTO.class);
        return clientDTO;
    }
    @Override
    public ClientDTO findClient(Long id){
        Optional<Client> client = Optional.ofNullable(clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found")));
        ClientDTO clientDTO = mapper.map(client.get(), ClientDTO.class);
        return clientDTO;
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO){
        Optional<Client> opt = clientRepository.findById(clientDTO.getId());
        Client client = new Client();
        if(opt.isPresent()){
            log.info("reached here");
            client = opt.get();
            client.setFirstname(clientDTO.getFirstname());
            client.setLastname(clientDTO.getLastname());
            client.setEmail(clientDTO.getEmail());
            client.setPhone(clientDTO.getPhone());
            client.setBirthDate(clientDTO.getBirthDate());
            client =clientRepository.save(client);
        }
        return mapper.map(client, ClientDTO.class);
    }
    @Override
    public List<ClientDTO> deleteClient(Long id) {
        Optional<Client> opt = Optional.ofNullable(clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found")));
        clientRepository.deleteById(opt.get().getId());
        log.info(opt.get().getFirstname()+" deleted");
        return this.getClients();
    }

    @Override
    public ClientDTO favoriteClient() {
        List<Client> listClient = clientRepository.findAll();
        Client client = new Client();
        Integer Max = 0;
        for(Client c : listClient){
            if(Max < c.getTickets().size()){
                Max = c.getTickets().size();
                client = c;
            }
        }
        return mapper.map(client, ClientDTO.class);
    }

    @Override
    public DayOfWeek getMostReservedDay(Long id){
        Optional<Client> opt = clientRepository.findById(id);
        List<DayOfWeek> days = opt.get().getTickets().stream()
                .map(m-> m.getDate().getDayOfWeek()).collect(Collectors.toList());
        Integer Max = 0;
        DayOfWeek d = null;
        for (DayOfWeek day : days){
            Integer frequency = Collections.frequency(days , day);
            if (Max < frequency){
                Max = frequency;
                d = day;
            }
        }

        return d;
    }

}
