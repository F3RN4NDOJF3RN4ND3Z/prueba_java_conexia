/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Fernandojavier
 */
@Entity
@Table(name="mesa")
public class Mesa implements Serializable {
    @Id
    @Column(name="id_mesa")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column
    private int num_max_comensales;
    @Column
    private String ubicacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_max_comensales() {
        return num_max_comensales;
    }

    public void setNum_max_comensales(int num_max_comensales) {
        this.num_max_comensales = num_max_comensales;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Mesa(int id, int num_max_comensales, String ubicacion) {
        this.id = id;
        this.num_max_comensales = num_max_comensales;
        this.ubicacion = ubicacion;
    }

    public Mesa() {
    }

    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", num_max_comensales=" + num_max_comensales + ", ubicacion=" + ubicacion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mesa other = (Mesa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
