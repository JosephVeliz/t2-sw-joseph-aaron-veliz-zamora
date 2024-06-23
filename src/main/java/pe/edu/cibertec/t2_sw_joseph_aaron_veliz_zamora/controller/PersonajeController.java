package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.exception.ResourceNotFoundException;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.model.bd.Personaje;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.service.PersonajeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/personajes")
public class PersonajeController {

    private PersonajeService personajeService;
    @GetMapping("")
    public ResponseEntity<List<Personaje>> listarPersonajes(){
        List<Personaje> personajes = new ArrayList<>(personajeService.listarPersonajes());
        if(personajes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(personajes, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPersonajexId(@PathVariable Integer id){
        Personaje personaje = personajeService.personajexId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El personaje con Id " + id + " no existe"));
        return new ResponseEntity<>(personaje, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Personaje> guardarPersonaje(@RequestBody Personaje personaje){
        return new ResponseEntity<>(personajeService.guardarPersonaje(personaje), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> actualizarPersonaje(@PathVariable Integer id, @RequestBody Personaje personaje) {
        Optional<Personaje> personajeExistente = personajeService.personajexId(id);
        if (personajeExistente.isPresent()) {
            personaje.setIdPersonaje(id);
            Personaje actualizadoPersonaje = personajeService.guardarPersonaje(personaje);
            return ResponseEntity.ok(actualizadoPersonaje);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarxId(@PathVariable Integer id) {
        if (personajeService.personajexId(id).isPresent()) {
            personajeService.eliminarxId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ResourceNotFoundException("El personaje con Id " + id + " no existe");
        }
    }
}