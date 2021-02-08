package ds.example.dsproject.controllers;


import ds.example.dsproject.dto.TicketDTO;
import ds.example.dsproject.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    @GetMapping
    public List<TicketDTO> ticketList(){

        return ticketService.getTickets();
    }

   @PostMapping("/{id}")
    public TicketDTO ticketAdd(@RequestBody TicketDTO ticketDTO,@PathVariable("id") Long id){

        return ticketService.addTicket(ticketDTO,id);
    }

    @DeleteMapping("/{id}")
    public List<TicketDTO> ticketDelete(@PathVariable("id") Long id) {
        return ticketService.deleteTicket(id);
    }

    @PutMapping
    @ResponseBody
    public TicketDTO ticketUpdate(@RequestBody TicketDTO ticketDTO){
        return  ticketService.updateTicket(ticketDTO);
    }

    @GetMapping("/income")
    @ResponseBody
    public Double Income (@RequestParam String d , @RequestParam String s){
        LocalDate date = LocalDate.parse(d);
        return ticketService.getIncome(date,s);
    }
    @GetMapping("/period")
    @ResponseBody
    public Double IncomePerPeriod (@RequestParam String d1, @RequestParam String d2 ) {
        LocalDate m1 = LocalDate.parse(d1);
        LocalDate m2 = LocalDate.parse(d2);
        return ticketService.getIncomePerPeriod(m1, m2);
    }
}
