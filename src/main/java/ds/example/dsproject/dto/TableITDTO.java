package ds.example.dsproject.dto;

import lombok.*;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TableITDTO {
    private Long id;

    private Integer number;
    private Integer nbCouvert;
    private String  type;
    private Double  supplement;
}
