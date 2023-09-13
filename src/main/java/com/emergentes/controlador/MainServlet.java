
package com.emergentes.controlador;

import com.emergentes.Bebidas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
            Bebidas objbeb = new Bebidas();
            int id, pos;
            
            HttpSession ses = request.getSession();
            ArrayList<Bebidas> lista = (ArrayList<Bebidas>) ses.getAttribute("listabeb");
           
            switch(op) {
                case "nuevo":
                    //envair un objeto vacio para ingresar datos
                    request.setAttribute("miobjbeb", objbeb);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    break;
                case "editar":
                    //modifiacr obteniendo el Id
                    id = Integer.parseInt(request.getParameter("id"));
                    pos = buscarPorIndice(request, id);
                    //ponerle valorea
                    objbeb = lista.get(pos);
                    //para rellenar
                    request.setAttribute("miobjbeb", objbeb);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    break;
                case "eliminar":
                    //Eliminar obteniendo el id de la persona
                    id = Integer.parseInt(request.getParameter("id"));
                    //buscamosn la posisciom
                    pos = buscarPorIndice(request, id);
                    
                    if(pos >= 0){
                        lista.remove(pos);
                    }
                    request.setAttribute("listabeb", lista);
                    response.sendRedirect("index.jsp");
                    break;
                    default:    
                
            }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          int id = Integer.parseInt(request.getParameter("id"));
        
        HttpSession ses = request.getSession();
        ArrayList<Bebidas> lista = (ArrayList<Bebidas>) ses.getAttribute("listabeb");
        Bebidas objbeb = new Bebidas();
        objbeb.setId(id);
        objbeb.setDescripcion(request.getParameter("descripcion"));
        objbeb.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        objbeb.setPrecio(Float.parseFloat(request.getParameter("precio")));
        objbeb.setCategoria(request.getParameter("categoria"));
        if(id == 0){
            //nuevo registro
            int idNuevo = obtenerId(request);
            objbeb.setId(idNuevo);
            lista.add(objbeb);
        }else{
            //posiscion
            int pos = buscarPorIndice(request, id);
            lista.set(pos, objbeb);
        }
        request.setAttribute("listabeb", lista);
        response.sendRedirect("index.jsp");
    }

    private int buscarPorIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        ArrayList<Bebidas> lista = (ArrayList<Bebidas>) ses.getAttribute("listabeb");
        
        int pos = -1;
        
        if(lista != null){
            for (Bebidas ele : lista) {
                ++pos;
                if (ele.getId() == id) {
                    break;
                }
            }
        }
        return pos;
    }

    private int obtenerId(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ArrayList<Bebidas> lista = (ArrayList<Bebidas>) ses.getAttribute("listabeb");
        
        //allamos en la lista
        int idn = 0;
        for (Bebidas ele : lista) {
            idn = ele.getId();
        }
        return idn + 1;
    }

}
