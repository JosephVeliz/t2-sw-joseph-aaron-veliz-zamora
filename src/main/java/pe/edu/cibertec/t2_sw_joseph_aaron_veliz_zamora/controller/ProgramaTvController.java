package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.controller;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.exception.ResourceNotFoundException;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.model.bd.ProgramaTv;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.service.IProgramaTvService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/programas")
public class ProgramaTvController {
    private IProgramaTvService programaTvService;

    @GetMapping("")
    public ResponseEntity<List<ProgramaTv>> listarProgramas() {
        List<ProgramaTv> programas = new ArrayList<>(programaTvService.listarProgramas());
        if (programas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(programas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaTv> obtenerProgramaPorId(@PathVariable Integer id) {
        ProgramaTv programaTv = programaTvService.programaxId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El programa con Id " + id + " no existe"));
        return new ResponseEntity<>(programaTv, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProgramaTv> registrarPrograma(@RequestBody ProgramaTv programaTv) {
        return new ResponseEntity<>(programaTvService.guardarPrograma(programaTv), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaTv> actualizarPrograma(@PathVariable Integer id, @RequestBody ProgramaTv programaTv) {
        ProgramaTv programaExistente = programaTvService.programaxId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El programa con Id " + id + " no existe"));
        programaExistente.setTitulo(programaTv.getTitulo());
        programaExistente.setResumen(programaTv.getResumen());
        programaExistente.setFechaInicio(programaTv.getFechaInicio());
        return new ResponseEntity<>(programaTvService.guardarPrograma(programaExistente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrograma(@PathVariable Integer id) {
        if (programaTvService.programaxId(id).isPresent()) {
            programaTvService.eliminarxId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ResourceNotFoundException("El programa con Id " + id + " no existe");
        }
    }
}
