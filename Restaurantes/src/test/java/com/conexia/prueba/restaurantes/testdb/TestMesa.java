/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.testdb;

import com.conexia.prueba.restaurantes.dao.MesaDAO;
import com.conexia.prueba.restaurantes.model.Mesa;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author Fernandojavier
 */
public class TestMesa {
    private Mesa mesa;
    private MesaDAO mesaDao;
    
    @Test
    public void getMesa(){
        mesa= new Mesa();
        mesaDao=new MesaDAO();
        mesa=mesaDao.findMesa(1);
        System.out.println(mesa.toString());
        assertEquals(mesa.getId(),1);
        
    }
}
