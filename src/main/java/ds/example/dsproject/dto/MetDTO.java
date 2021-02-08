package ds.example.dsproject.dto;



import lombok.*;

import javax.validation.constraints.Pattern;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MetDTO {
    private Long id;

    private String name;
    private Double price;
    @Pattern(regexp = "^(Plat|Entree|Dessert){1}$", message = "you chose the Plat as the validated choice")
    private String type;

}
