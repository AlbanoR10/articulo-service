package com.articulo.service.modelo;

import com.articulo.service.entidad.Articulo;
import com.articulo.service.repositorio.ArticuloRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloModelo {

    @Autowired
    private ArticuloRepository articuloRepository;

    public List<Articulo> getAll() {
        return articuloRepository.findAll();
    }

    public Articulo getArticuloById(int d) {
        return articuloRepository.findById(d).orElse(null);
    }

    public Articulo save(Articulo articulo) {
        int longitudClave = articulo.getClave().length();
        if (articulo.getNombre().length() > 30)return null;

        if (longitudClave > 30 || longitudClave < 3)return null;
        
        switch (articulo.getUnidadMedida()) {
            case "1":
                articulo.setUnidadMedida("Pieza");
                break;
            case "2":
                articulo.setUnidadMedida("Kilogramo");
                break;
            case "3":
                articulo.setUnidadMedida("Pulgada");
                break;
            case "4":
                articulo.setUnidadMedida("Litro");
                break;
            default:
                return null;
        }

        Articulo nuevoArticulo = articuloRepository.save(articulo);
        return nuevoArticulo;
    }
}
