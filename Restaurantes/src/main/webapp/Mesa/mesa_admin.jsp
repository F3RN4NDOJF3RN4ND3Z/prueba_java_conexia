<%-- 
    Document   : mesa_admin
    Created on : Jun 9, 2019, 7:44:36 AM
    Author     : Fernandojavier
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar Mesas</title>
    </head>
    <body>
        <h1>Administrar Mesas</h1>
        <div>
            <a href="MesaController?action=Editar">Editar</a>
            <a href="MesaController?action=Agregar">Agregar</a>
            <a href="MesaController?action=Regresar">Regresar</a>
        </div>
        <div>
            <table>
                <thead>
                    <th>
                <tr>
                    <td>
                        ID
                    </td>
                    <td>
                        NUM. MAX. COMENSALES
                    </td>
                    <td>
                        UBICACION
                    </td>
                </tr>
                </th>
                </thead>
                
                <tbody>
                <c:forEach items="${listaMesas}" var="mesa">
                    <tr>
                        <td>
                            <c:out value="${mesa.id}"/>
                        </td>
                        <td>
                           <c:out value="${mesa.num_max_comensales}"/>
                        </td>
                        <td>
                            <c:out value="${mesa.ubicacion}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
