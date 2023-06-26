package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Libro;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.LibroService;
//import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.istateca.app.istateca.utilidades.Imagenes.*;

@RestController
@RequestMapping("/libro")
public class LibroController extends BaseController<Libro> {

    /*
    controladores basicos estan en controlador general
    crear: /libro/crear -> pasar requestbody
    listar: /libro/listar
    buscar: /libro/buscar/{id} -> pasar PathVariable("id")
    editar: /libro/editar/{id} -> pasar requestbody
    */

    @Autowired
    private LibroService service;

    @Value("${data_directory}"+"Libros")
    private String rutageneral;

    @Override
    protected BaseService<Libro, Integer> getService() {
        return service;
    }

    @GetMapping("/listarlibrosxnombre/{titulo}")
    public List<Libro> librosxnombre(@PathVariable String titulo){
        return service.librosxTitulo(titulo);
    }

    @GetMapping("/listarlibrosxtipo/{tipoid}")
    public List<Libro> librosxnombre(@PathVariable Integer tipoid){
        return service.librosxTipo(tipoid);
    }

    @GetMapping("/buscarxcoincidencia")
    public List<Libro> buscarLibros(@RequestParam(value = "parametro") String parametro) {
        return service.librosxcoincidencias(parametro);
    }

    @PostMapping("/subirimagen/{id}")
    public ResponseEntity<?> subirImagen(@PathVariable Integer id, @RequestParam("imagen") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            String resultado = subirArchivo(rutageneral, "libro_" + id, imagen);
            if (resultado.startsWith("Error")) {
                return ResponseEntity.badRequest().body(resultado);
            } else {
                Optional<Libro> libro = service.findById(id);
                resultado=resultado.substring(resultado.indexOf("/Archivos"));
                libro.get().setUrlImagen(resultado);
                service.save(libro.get());
                return ResponseEntity.ok().body("Libro Guardado en : "+resultado);
            }
        } else {
            return ResponseEntity.badRequest().body("La imagen está vacía.");
        }
    }

    /*endpoint que obtiene imagen, usar solo en caso de que falle el path directo,error de caracteres / a //
    @GetMapping("/obtenerimagen")
    public ResponseEntity<Resource> obtenerImagen(@RequestParam("ruta") String ruta) {
        return obtenerArchivo(ruta, 1);
    }*/
}
