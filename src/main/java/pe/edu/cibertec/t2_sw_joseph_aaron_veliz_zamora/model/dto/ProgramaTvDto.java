package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramaTvDto {
    private Integer idProgramaTv;
    private String titulo;
    private String resumen;
    private String fechaInicio;
    private Integer idPersonaje;
}
