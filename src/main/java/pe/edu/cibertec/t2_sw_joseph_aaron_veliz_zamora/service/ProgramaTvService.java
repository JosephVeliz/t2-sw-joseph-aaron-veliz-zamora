package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.model.bd.ProgramaTv;
import pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.repository.ProgramaTvRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaTvService implements IProgramaTvService {
    @Autowired
    private ProgramaTvRepository programaTvRepository;

    @Override
    public List<ProgramaTv> listarProgramas() {
        return programaTvRepository.findAll();
    }

    @Override
    public Optional<ProgramaTv> programaxId(Integer id) {
        return Optional.ofNullable(programaTvRepository.findById(id).orElse(null));

    }

    @Override
    public ProgramaTv guardarPrograma(ProgramaTv programaTv) {
        return programaTvRepository.save(programaTv);
    }

    @Override
    public void eliminarxId(Integer id) {
        programaTvRepository.deleteById(id);
    }
}
