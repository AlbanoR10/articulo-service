package com.articulo.service.repositorio;

import com.articulo.service.entidad.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer>{
    
}
