package ds.example.dsproject.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Entree")
@Setter
@Getter
@NoArgsConstructor
public class Entree extends Met{

}
