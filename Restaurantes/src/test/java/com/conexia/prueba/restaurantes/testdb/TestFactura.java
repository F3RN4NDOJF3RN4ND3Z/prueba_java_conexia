/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.testdb;

import com.conexia.prueba.restaurantes.dao.CamareroDAO;
import com.conexia.prueba.restaurantes.dao.ClienteDAO;
import com.conexia.prueba.restaurantes.dao.FacturaDAO;
import com.conexia.prueba.restaurantes.dao.MesaDAO;
import com.conexia.prueba.restaurantes.model.Camarero;
import com.conexia.prueba.restaurantes.model.Cliente;
import com.conexia.prueba.restaurantes.model.Factura;
import com.conexia.prueba.restaurantes.model.Mesa;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author Fernandojavier
 */
public class TestFactura {
    private Factura factura;
    private FacturaDAO facturaDao;
    
    @Test
    public void getFactura(){
        factura=new Factura();
        facturaDao= new FacturaDAO();
        factura=facturaDao.findFactura(1);
        System.out.println(factura.toString());
        assertEquals(factura.getId(),1);
    }
    
    @Test
    public void addFactura(){
        factura=new Factura();
        Mesa mesa= new Mesa();
        Cliente cliente= new Cliente();
        ClienteDAO clienteDAO= new ClienteDAO();
        MesaDAO mesaDAO=new MesaDAO();
        Camarero camarero= new Camarero();
        CamareroDAO camareroDAO= new CamareroDAO();
        camarero=camareroDAO.findCamarero(1);
        mesa=mesaDAO.findMesa(1);
        cliente=clienteDAO.findCliente(1);
        facturaDao= new FacturaDAO();
        factura.setCamarero(camarero);
        factura.setCliente(cliente);
        factura.setMesa(mesa);
        factura.setFecha_factura(new Date());
        int count=facturaDao.getFacturaCount();
        try {
            facturaDao.create(factura);
        } catch (Exception ex) {
            System.out.println("Error al insertar");
            System.out.println(ex);;
        }
        System.out.println(factura.toString());
        int countFinal=facturaDao.getFacturaCount();
        assertEquals(count,countFinal-1);
    }
}
