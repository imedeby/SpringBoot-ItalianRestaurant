package ds.example.dsproject.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private LocalDate date;
    private Double addition;
    private Integer nbCouvert;

    @ManyToOne
    private Client client;

    @ManyToOne
    private TableIT tableIT;

    @ManyToMany
    @JoinTable(name = "met_tickets",
        joinColumns = {@JoinColumn(name="tickets_id")},
        inverseJoinColumns = {@JoinColumn(name = "met_id")}
    )
    private List<Met> mets;

}
