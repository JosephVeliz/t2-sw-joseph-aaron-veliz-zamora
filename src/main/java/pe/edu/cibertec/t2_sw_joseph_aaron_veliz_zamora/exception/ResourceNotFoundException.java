package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
