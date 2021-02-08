package ds.example.dsproject.controllers;

import ds.example.dsproject.dto.ClientDTO;
import ds.example.dsproject.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.DayOfWeek;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    public List<ClientDTO> clientDTOList(){
        return clientService.getClients();
    }
    @GetMapping("/{id}")
    public ClientDTO findClientbyId(@PathVariable("id") Long id){
        return clientService.findClient(id);
    }
    @PostMapping
    public ClientDTO clientDTOadd(@RequestBody ClientDTO clientDTO){
        return clientService.addClient(clientDTO);
    }
    @DeleteMapping("/{id}")
    public List<ClientDTO> clientDTOdelete(@PathVariable("id") Long id){
        return clientService.deleteClient(id);
    }

    @PutMapping
    @ResponseBody
    public ClientDTO clientDTOupdate(@RequestBody ClientDTO clientDTO){
        return clientService.updateClient(clientDTO);
    }

    @GetMapping("/bestclient")
    public ClientDTO favoriteClient(){
        return clientService.favoriteClient();
    }

    @GetMapping("/bestday/{id}")
    public DayOfWeek MostReservedDay(@PathVariable("id") Long id){
        return clientService.getMostReservedDay(id);
    }
}
