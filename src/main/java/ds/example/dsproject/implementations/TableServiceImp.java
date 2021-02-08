package ds.example.dsproject.implementations;


import ds.example.dsproject.dto.TableITDTO;
import ds.example.dsproject.entities.TableIT;
import ds.example.dsproject.entities.Ticket;
import ds.example.dsproject.repositories.TableRepository;
import ds.example.dsproject.repositories.TicketRepository;
import ds.example.dsproject.services.TableService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TableServiceImp implements TableService {
    private TableRepository tableRepository;
    private TicketRepository ticketRepository;
    private ModelMapper mapper;

    @Override
    public List<TableITDTO> getTables() {
        List<TableITDTO> liste = tableRepository.findAll().stream().map(t -> mapper.map(t,TableITDTO.class)).collect(Collectors.toList());
        return liste;
    }
    @Override
    public TableITDTO addTable(TableITDTO tableITDTO){
        TableIT tableIT = mapper.map(tableITDTO, TableIT.class);
        tableIT = tableRepository.save(tableIT);
        tableITDTO = mapper.map(tableIT, TableITDTO.class);
        return tableITDTO;
    }

    @Override
    public List<TableITDTO> deleteTable(Long id){
        Optional<TableIT> opt = Optional.ofNullable(tableRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Table not found")));
        tableRepository.delete(opt.get());
        return this.getTables();
    }

    @Override
    public  TableITDTO updateTable(TableITDTO tableITDTO){
        Optional<TableIT> opt = tableRepository.findById(tableITDTO.getId());
        TableIT table = new TableIT();
        if (opt.isPresent()){
            table = opt.get();
            table.setNumber(tableITDTO.getNumber());
            table.setNbCouvert(tableITDTO.getNbCouvert());
            table.setSupplement(tableITDTO.getSupplement());
            table.setSupplement(tableITDTO.getSupplement());
            table.setType(tableITDTO.getType());
            tableRepository.save(table);
        }
        return mapper.map(table, TableITDTO.class);
    }

    @Override
    public TableITDTO MostReserved (){
        List<Ticket> tickets = ticketRepository.findAll();
        TableIT table = new TableIT();
        Integer Max = 0;
        HashMap<TableIT, Integer> tables = new HashMap<>();
        for( Ticket ticket : tickets ){
            if (tables.containsKey((TableIT) ticket.getTableIT())){
                tables.put(ticket.getTableIT(), tables.get(ticket.getTableIT()) + 1);
                if( Max < tables.get(ticket.getTableIT())){
                    Max = tables.get(ticket.getTableIT());
                    table = ticket.getTableIT();
                }
            }
            else{
                tables.put(ticket.getTableIT(), 1);
            }
        }

        return  mapper.map(table, TableITDTO.class);
    }
}
