package ds.example.dsproject.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Dessert")
@Setter
@Getter
@NoArgsConstructor
public class Dessert extends Met{

}
