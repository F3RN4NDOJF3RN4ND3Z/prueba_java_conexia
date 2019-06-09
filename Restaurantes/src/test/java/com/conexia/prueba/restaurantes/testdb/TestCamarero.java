/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.testdb;

import com.conexia.prueba.restaurantes.dao.CamareroDAO;
import com.conexia.prueba.restaurantes.model.Camarero;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 *
 * @author Fernandojavier
 */
public class TestCamarero {
    private Camarero camarero;
    private CamareroDAO camareroDAO;
    
    @Test
    public void getCamarero(){
        camarero= new Camarero();
        camareroDAO= new CamareroDAO();
        camarero=camareroDAO.findCamarero(1);
        System.out.println(camarero.toString());
        assertEquals(camarero.getId(), 1);
    }
}
