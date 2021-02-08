package ds.example.dsproject.entities;


import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Plat")
@Setter
@Getter
@NoArgsConstructor
public class Plat extends Met{


}
