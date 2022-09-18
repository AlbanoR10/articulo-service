/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.articulo.service.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "unidad_medida", nullable = false)
    private String unidadMedida;

    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Override
    public String toString() {
        return "{id: "+id+", nombre:"+nombre+", unidadMedida:"+unidadMedida+", clave:"+clave+", precio:"+precio+"}";
    }
}
