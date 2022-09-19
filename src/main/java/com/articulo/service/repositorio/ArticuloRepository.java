package com.articulo.service.repositorio;

import com.articulo.service.entidad.Articulo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, String>{
    
}
