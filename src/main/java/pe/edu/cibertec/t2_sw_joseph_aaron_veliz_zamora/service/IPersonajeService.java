package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.service;

import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.model.bd.Personaje;

import java.util.List;
import java.util.Optional;

public interface IPersonajeService {
    List<Personaje> listarPersonajes();
    Optional<Personaje> personajexId(Integer id);
    Personaje guardarPersonaje(Personaje personaje);
    void eliminarxId(Integer id);
}

