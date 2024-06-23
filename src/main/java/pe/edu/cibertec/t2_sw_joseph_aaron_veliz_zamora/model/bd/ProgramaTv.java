package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.model.bd;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ProgramaTv")
public class ProgramaTv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProgramaTv")
    private Integer idProgramaTv;
    @Column(name = "Titulo", nullable = false, length = 250)
    private String titulo;
    @Column(name = "Resumen", nullable = false, length = 250)
    private String resumen;
    @Column(name = "FechaInicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @ManyToOne
    @JoinColumn(name = "IdPersonaje", nullable = false)
    private Personaje personaje;
}