/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.testdb;

import com.conexia.prueba.restaurantes.dao.DetalleFacturaDAO;
import com.conexia.prueba.restaurantes.model.DetalleFactura;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author Fernandojavier
 */
public class TestDetalleFactura {
    private DetalleFactura df;
    private DetalleFacturaDAO dfDao;
    
    @Test
    public void getDetalleFactura(){
        df=new DetalleFactura();
        dfDao= new DetalleFacturaDAO();
        df=dfDao.findDetalleFactura(2);
        System.out.println(df.toString());
        assertEquals(df.getId(),2);
    }
}
