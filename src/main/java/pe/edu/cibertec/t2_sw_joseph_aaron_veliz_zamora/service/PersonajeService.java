package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.model.bd.Personaje;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.repository.PersonajeRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonajeService implements IPersonajeService {
    private PersonajeRepository personajeRepository;


    @Override
    public List<Personaje> listarPersonajes() {
        return personajeRepository.findAll();
    }

    @Override
    public Optional<Personaje> personajexId(Integer id) {
        Optional<Personaje> personaje
                = personajeRepository.findById(id);
        if(personaje.isEmpty()){
            return Optional.empty();
        }
        return personaje;
    }

    @Override
    public Personaje guardarPersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    @Override
    public void eliminarxId(Integer id) {
        personajeRepository.deleteById(id);
    }
}
