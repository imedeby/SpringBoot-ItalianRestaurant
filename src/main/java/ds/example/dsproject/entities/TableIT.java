package ds.example.dsproject.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TableIT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private Integer nbCouvert;
    private String  type;
    private Double  supplement;

    @OneToMany
    private List<Ticket> tickets;


}
