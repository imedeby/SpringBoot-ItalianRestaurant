package ds.example.dsproject.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import lombok.Data;



import javax.persistence.*;
import java.util.List;

@Entity
@Data
@DiscriminatorColumn(name = "Type")
@JsonSubTypes({ @Type(name = "Dessert", value = Dessert.class), @Type(name = "Entree", value = Entree.class),
        @Type(name = "Plat", value = Plat.class) })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract  class Met {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @ManyToMany(mappedBy = "mets")
    private List<Ticket> tickets;

    public String getType() {

        return this.getClass().getSimpleName();
    }
}

