package ds.example.dsproject.entities;

import lombok.*;


import javax.persistence.*;

import java.time.LocalDate;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstname;

    private String lastname;

    private LocalDate birthDate;

    private  String email;

    private  String phone;
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;

}
