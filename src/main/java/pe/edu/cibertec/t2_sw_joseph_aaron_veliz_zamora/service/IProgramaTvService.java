package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.service;

import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.model.bd.ProgramaTv;

import java.util.List;
import java.util.Optional;

public interface IProgramaTvService {
    List<ProgramaTv> listarProgramas();
    Optional<ProgramaTv> programaxId(Integer id);
    ProgramaTv guardarPrograma(ProgramaTv programaTv);
    void eliminarxId(Integer id);
}