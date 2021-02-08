package ds.example.dsproject.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    @NotBlank(message = "FirstName is mandatory")
    private String firstname;
    @NotBlank(message = "LastName is mandatory")
    private String lastname;
    @NotBlank(message = "BirthDate is mandatory")
    private LocalDate birthDate;
    @NotBlank(message = "Email is mandatory")
    private  String email;
    @NotBlank(message = "Phone is mandatory")
    private  String phone;
}
