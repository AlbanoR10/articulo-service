package com.articulo.service.controlador;

import com.articulo.service.entidad.Articulo;
import com.articulo.service.modelo.ArticuloModelo;
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

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerArticulo(@PathVariable("id") int id) {
        Articulo articulo = articuloModelo.getArticuloById(id);
        if (articulo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(articulo);
    }

    @PostMapping
    public ResponseEntity<Articulo> guardarArticulo(@RequestBody Articulo bitacora) {
        Articulo nuevoArticulo = articuloModelo.save(bitacora);
        if (nuevoArticulo == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(nuevoArticulo);
    }

    @GetMapping
    public ResponseEntity<List<Articulo>> listarArticulo(){
        List<Articulo> articulos = articuloModelo.getAll();
        if(articulos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articulos);
    }
}
