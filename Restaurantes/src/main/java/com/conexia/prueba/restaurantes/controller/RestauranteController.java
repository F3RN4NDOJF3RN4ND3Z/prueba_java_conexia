/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexia.prueba.restaurantes.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernandojavier
 */
@WebServlet(name = "RestauranteController", urlPatterns = {"/RestauranteController"})
public class RestauranteController extends HttpServlet {

    private static final Logger logger= Logger.getGlobal();
    
    public void init(){
        
    }
    
     public RestauranteController(){
        super();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String action = request.getParameter("action");
            System.out.println(action);
		try {
			switch (action) {
			case "mesaAdmin":
				//index(request, response);
                                adminMesas(request,response);
				break;
			case "nuevo":
				//nuevo(request, response);
				break;
			case "register":
				System.out.println("entro");
				//registrar(request, response);
				break;
			case "mostrar":
				//mostrar(request, response);
				break;
			case "showedit":
				//showEditar(request, response);
				break;	
			case "editar":
				//editar(request, response);
				break;
			case "eliminar":
				//eliminar(request, response);
				break;
			default:
				break;
			}			
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Error en la accion recibida "+ action,e);
		}
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //processRequest(request, response);
        RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void adminMesas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher= request.getRequestDispatcher("Mesa/mesa_admin.jsp");
        dispatcher.forward(request, response);
    }

}
