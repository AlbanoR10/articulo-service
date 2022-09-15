package com.articulo.service.controlador;

import com.articulo.service.entidad.Articulo;
import com.articulo.service.modelo.ArticuloModelo;
import com.articulo.service.publisher.Publisher;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articulo")
public class ArticuloController {

    @Autowired
    private ArticuloModelo articuloModelo;

    @Autowired
    private Publisher publisher;

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerArticulo(@PathVariable("id") int id) {
        Articulo articulo = articuloModelo.getArticuloById(id);
        if (articulo == null) {
            publisher.send("No se leyo el articulo con id " + id);
            return ResponseEntity.notFound().build();
        }
        publisher.send("Se leyo el articulo con id " + id);
        return ResponseEntity.ok(articulo);
    }

    @PostMapping
    public ResponseEntity<Articulo> guardarArticulo(@RequestBody Articulo articulo) {
        Articulo nuevoArticulo = articuloModelo.save(articulo);
        if (nuevoArticulo == null) {
            publisher.send("No se guardo el articulo");
            return ResponseEntity.unprocessableEntity().build();
        }
        publisher.send("Se guardo articulo con id: " + nuevoArticulo.getId());
        return ResponseEntity.ok(nuevoArticulo);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Articulo> actualizarArticulo(@RequestBody Articulo articulo) {
        Articulo nuevoArticulo = articuloModelo.actualizar(articulo);
        if (nuevoArticulo == null) {
            publisher.send("No se actualizo el articulo con id: " + articulo.getId());
            return ResponseEntity.unprocessableEntity().build();
        }
        publisher.send("Se actualizo el articulo con id: " + nuevoArticulo.getId());
        return ResponseEntity.ok(nuevoArticulo);
    }

    @GetMapping
    public ResponseEntity<List<Articulo>> listarArticulo() {
        List<Articulo> articulos = articuloModelo.getAll();
        if (articulos.isEmpty()) {
            publisher.send("No hay articulos para listar");
            return ResponseEntity.noContent().build();
        }
        publisher.send("Se listaron todos los articulos");
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/info")
    public ResponseEntity<String> informacionArticulos() {
        
        publisher.send("Se pidió informacion");
        String info = "Campo nombre: Texto de entre 1 y 30 caracteres\n"+
                        "Campo unidadMedida: 1=Pieza, 2=Kilogramo, 3=Pulgada, 4=Litro\n"+
                        "Campo clave: Texto de entre 3 y 30 caracteres\n"+
                        "Campo precio: Numero decimal";

        return ResponseEntity.ok(info);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id) {
        int respuesta = articuloModelo.eliminar(id);
        if (respuesta==0) {
            publisher.send("No existia articulo con id " + id);
            return "No existia articulo con id " + id;
        }
        publisher.send("Se elimino articulo con id " + id);
        return "Se elimino articulo con id " + id;
    }
}
