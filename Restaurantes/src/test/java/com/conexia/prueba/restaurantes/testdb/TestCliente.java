/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.testdb;
import com.conexia.prueba.restaurantes.dao.ClienteDAO;
import com.conexia.prueba.restaurantes.model.Cliente;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author Fernandojavier
 */
public class TestCliente {
    
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    
    @Test
    public void getClientes(){
       
        cliente= new Cliente();
        clienteDAO= new ClienteDAO();
        cliente=clienteDAO.findCliente(1);
        System.out.println(cliente.toString());
        assertEquals(cliente.getNombre(), "prueba");
        
    }
}
