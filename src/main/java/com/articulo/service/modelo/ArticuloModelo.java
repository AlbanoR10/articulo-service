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

    public Iterable<Articulo> getAll() {
        return articuloRepository.findAll();
    }

    public Articulo getArticuloById(String d) {
        return articuloRepository.findById(d).orElse(null);
    }

    public Articulo save(Articulo articulo) {
        int longitudClave = articulo.getClave().length();
        int longitudNombre = articulo.getNombre().length();
        if (longitudNombre > 30 || longitudNombre==0) {
            return null;
        }

        if (longitudClave > 30 || longitudClave < 3) {
            return null;
        }

        if(articulo.getPrecio()<0) return null;

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

    public Articulo actualizar(Articulo articulo) {
        Articulo articuloAnterior = getArticuloById(articulo.getId());
        if (articuloAnterior == null)return null;
        

        int longitudClave = articulo.getClave().length();
        int longitudNombre = articulo.getNombre().length();

        if (longitudNombre > 30 || longitudNombre==0) {
            return null;
        }

        if (longitudClave > 30 || longitudClave < 3) {
            return null;
        }

        if(articulo.getPrecio()<0) return null;

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
            case "Pieza":
            case "Kilogramo":
            case "Pulgada":
            case "Litro":
                break;
            default:
                return null;
        }

        Articulo nuevoArticulo = articuloRepository.save(articulo);
        return nuevoArticulo;
    }

    public int eliminar(String id) {
        Articulo articuloAnterior = getArticuloById(id);
        if (articuloAnterior == null){
            return 0;
        }else{
            articuloRepository.delete(articuloAnterior);
            return 1;
        }
    }
}
