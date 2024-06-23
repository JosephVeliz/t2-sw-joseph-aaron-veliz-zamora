package pe.edu.cibertec.t2_sw_joseph_aaron_veliz_zamora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {
    private static final List<String> EXTENSIONES_VALIDAS = Arrays.asList("pdf", "png", "docx");
    private static final long TAMANO_MAXIMO_ARCHIVO = 30 * 1080 * 1080;

    @PostMapping("/subirMultiples")
    public ResponseEntity<String> subirArchivosMultiples(@RequestParam("archivos") MultipartFile[] archivos) {
        List<String> archivosSubidos = Arrays.stream(archivos)
                .map(archivo -> {
                    String extensionArchivo = obtenerExtensionArchivo(archivo.getOriginalFilename());
                    if (!EXTENSIONES_VALIDAS.contains(extensionArchivo)) {
                        return "Tipo de archivo no válido: " + extensionArchivo;
                    }
                    if (archivo.getSize() > TAMANO_MAXIMO_ARCHIVO) {
                        return "El tamaño del archivo supera el límite de 25MB: " + archivo.getOriginalFilename();
                    }
                    try {
                        archivo.transferTo(new File("uploads/" + archivo.getOriginalFilename()));
                        return archivo.getOriginalFilename();
                    } catch (IOException e) {
                        return "Error al subir " + archivo.getOriginalFilename() + ": " + e.getMessage();
                    }
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok("Archivos subidos correctamente: " + archivosSubidos);
    }

    @PostMapping("/subirUnico")
    public ResponseEntity<String> subirArchivoUnico(@RequestParam("archivo") MultipartFile archivo) {
        String extensionArchivo = obtenerExtensionArchivo(archivo.getOriginalFilename());
        if (!EXTENSIONES_VALIDAS.contains(extensionArchivo)) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Tipo de archivo no válido: " + extensionArchivo);
        }
        if (archivo.getSize() > TAMANO_MAXIMO_ARCHIVO) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("El tamaño del archivo supera el límite de 25MB");
        }
        try {
            archivo.transferTo(new File("uploads/" + archivo.getOriginalFilename())); // Ruta para guardar el archivo
            return ResponseEntity.ok("Archivo subido correctamente: " + archivo.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir archivo: " + e.getMessage());
        }
    }

    private String obtenerExtensionArchivo(String nombreArchivo) {
        return nombreArchivo.substring(nombreArchivo.lastIndexOf('.') + 1).toLowerCase();
    }
}