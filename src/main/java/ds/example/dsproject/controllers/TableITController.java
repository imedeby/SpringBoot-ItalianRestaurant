package ds.example.dsproject.controllers;


import ds.example.dsproject.dto.TableITDTO;
import ds.example.dsproject.services.TableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/table")
public class TableITController {

    private TableService tableService;
    @GetMapping
    public List<TableITDTO> tableITDTOSList(){

        return tableService.getTables();
    }
    @PostMapping
    public TableITDTO tableITDTOAdd(@RequestBody TableITDTO tableITDTO){

        return tableService.addTable(tableITDTO);
    }

    @DeleteMapping("{id}")
    public List<TableITDTO> tableITDTODelete (@PathVariable Long id){
        return tableService.deleteTable(id);
    }

    @PutMapping
    public TableITDTO tableITDTOUpdate (@RequestBody TableITDTO tableITDTO){
        return tableService.updateTable(tableITDTO);
    }

    @GetMapping("/besttable")
    public TableITDTO tableMostReserved (){
        return tableService.MostReserved();
    }
}
