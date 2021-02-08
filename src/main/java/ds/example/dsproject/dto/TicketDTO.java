package ds.example.dsproject.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    private Long id;
    private Integer number;
    private LocalDate date;
    private Double addition;
    private Integer nbCouvert;

    private ClientDTO client;

    private TableITDTO tableIT;

    private List<MetDTO> mets;
}
