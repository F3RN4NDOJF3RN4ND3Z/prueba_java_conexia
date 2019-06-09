/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.testdb;

import com.conexia.prueba.restaurantes.dao.CocineroDAO;
import com.conexia.prueba.restaurantes.model.Cocinero;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author Fernandojavier
 */
public class TestCocinero {
    private Cocinero cocinero;
    private CocineroDAO cocineroDAO;
    
    @Test
    public void getCocinero(){
        cocinero=new Cocinero();
        cocineroDAO= new CocineroDAO();
        cocinero= cocineroDAO.findCocinero(1);
        System.out.println(cocinero.toString());
        assertEquals(cocinero.getId(),1);
    }
}
