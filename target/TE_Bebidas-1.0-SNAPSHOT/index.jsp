<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.Bebidas"%>
<%
    if(session.getAttribute("listabeb") == null){
        ArrayList<Bebidas> lisaux = new ArrayList<Bebidas>();
        session.setAttribute("listabeb", lisaux);
    }
    ArrayList<Bebidas> lista = (ArrayList<Bebidas>) session.getAttribute("listabeb");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
       
        div {
            border: 1px solid #ccc; 
            padding: 20px; 
            text-align: center; 
        }
        
        body {
            text-align: center; 
        }
        h1 {
            margin-top: 20px; 
        }
        table {
            margin: 0 auto;
        }
        a{
            text-align: left;
        }
    </style>
    </head>
    <body>
        <div>
            <p>SEGUNDO PARCIAL TEM-742</p>
            <p>Nombre: Sadam Jose Quispe Chino</p>
            <p>Carnet: 9965092 L.P.</p>
        </div>
        <h1>Gestion de productos</h1>
        
        <a href="MainServlet?op=nuevo" class="a">Nuevo producto</a>
        <table border='1'>
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <%
                if (lista != null) {
                        for (Bebidas item : lista) {
                               
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getDescripcion() %></td>
                <td><%= item.getCantidad() %></td>
                <td><%= item.getPrecio() %></td>
                <td><%= item.getCategoria() %></td>
                <td>
                    <a href="MainServlet?op=editar&id=<%= item.getId() %>">Editar</a>
                </td>
                <td>
                    <a href="MainServlet?op=eliminar&id=<%= item.getId() %>"
                       onclick="return(confirm('Esta seguro que quiere eliminar'))"
                       >Eliminar</a>
                </td>
            </tr>
            <%
                  }
                } 
            %>
        </table>
    </body>
</html>
