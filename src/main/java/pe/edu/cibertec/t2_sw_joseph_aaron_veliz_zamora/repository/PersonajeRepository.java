package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.model.bd.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
}
