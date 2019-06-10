/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.controller;

import com.conexia.prueba.restaurantes.dao.DAOMesa;
import com.conexia.prueba.restaurantes.dao.MesaDAO;
import com.conexia.prueba.restaurantes.model.Mesa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Fernandojavier
 */
@WebServlet(name="MesaController",urlPatterns="/MesaController")
public class MesaController extends HttpServlet {
    
    private static final Logger logger= Logger.getLogger(MesaController.class.getName());
     
    private DAOMesa mesaDAO;
    private List<Mesa> listaMesas=new ArrayList<>();
    public MesaController(){
        super();
        
    }
    @PostConstruct
    public void inicializarMesas(){
        mesaDAO= new DAOMesa();        
        
    }
    public void init(){
       try{
            logger.log(Level.INFO,"Mesas Init");
           
            
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error al listar mesas",e);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        
        String action = request.getParameter("action");
                System.out.println("Mesa controller action");
		System.out.println(action);
		try {
			switch (action) {
			case "Regresar":
				index(request, response);
				break;
			case "Listar":
				listar(request, response);
				break;
			case "Editar":
				editar(request, response);
				break;
			case "Agregar":
				agregar(request, response);
				break;
			default:
				break;
			}			
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Error en la accion recibida "+ action,e);
		}
    
    }
    private void index (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//mostrar(request, response);
        RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
 
    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher= request.getRequestDispatcher("Mesa/editar.jsp");
        dispatcher.forward(request, response);
    }
	
    private void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("Mesa/agregar.jsp");
        dispatcher.forward(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listaMesas", listaMesas);
	RequestDispatcher dispatcher = request.getRequestDispatcher("Mesa/mesa_admin.jsp");
	dispatcher.forward(request, response);
    }
	
	
    

            
    
}
