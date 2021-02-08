package ds.example.dsproject.services;

import ds.example.dsproject.dto.ClientDTO;


import java.time.DayOfWeek;
import java.util.List;

public interface ClientService {
    List<ClientDTO> getClients();
    ClientDTO addClient(ClientDTO clientDTO);
    List<ClientDTO> deleteClient(Long id);
    ClientDTO findClient(Long id);
    ClientDTO updateClient(ClientDTO clientDTO);
    ClientDTO favoriteClient();
    DayOfWeek getMostReservedDay(Long id);
}
