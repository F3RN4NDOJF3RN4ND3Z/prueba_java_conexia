/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


/**
 *
 * @author Fernandojavier
 */
@Entity
@Table(name="factura")
public class Factura implements Serializable{
    @Id
    @Column(name="id_factura")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="factura_id_factura_seq")
    @SequenceGenerator(name="factura_id_factura_seq", sequenceName="factura_id_factura_seq", allocationSize=1)
    
    private int id;
    @Column
    private Date fecha_factura;
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_mesa")
    private Mesa mesa;
    @ManyToOne
    @JoinColumn(name="id_camarero")
    private Camarero camarero;
    

    public Factura() {
    }

    public Factura(int id, Date fecha_factura, Cliente cliente, Mesa mesa, Camarero camarero) {
        this.id = id;
        this.fecha_factura = fecha_factura;
        this.cliente = cliente;
        this.mesa = mesa;
        this.camarero = camarero;
    
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
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
        final Factura other = (Factura) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", fecha_factura=" + fecha_factura + ", cliente=" + cliente.toString() + ", mesa=" + mesa.toString() + ", camarero=" + camarero.toString() +  '}';
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Camarero getCamarero() {
        return camarero;
    }

    public void setCamarero(Camarero camarero) {
        this.camarero = camarero;
    }

    
    
    
}
