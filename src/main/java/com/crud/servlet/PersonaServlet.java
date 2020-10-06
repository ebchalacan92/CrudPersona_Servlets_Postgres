package com.crud.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.crud.persona.Persona;
import com.crud.servicios.ServicioPersona;




@WebServlet("/")
public class PersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private ServicioPersona servicio;
       
   
    public PersonaServlet() {
        super();
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        String action = request.getServletPath();

    	        try {
    	            switch (action) {
    	                case "/new":
    	                    showNewForm(request, response);
    	                    break;
    	                case "/insert":
    	                    insertUser(request, response);
    	                    break;
    	                case "/delete":
    	                    deleteUser(request, response);
    	                    break;
    	                case "/edit":
    	                    showEditForm(request, response);
    	                    break;
    	                case "/update":
    	                    updateUser(request, response);
    	                    break;
    	                default:
    	                    listUser(request, response);
    	                    break;
    	            }
    	        } catch (SQLException ex) {
    	            throw new ServletException(ex);
    	        }
    	    }

    	    private void listUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	        List <Persona> listUser = servicio.buscarTodos();
    	        request.setAttribute("listUser", listUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
    	        dispatcher.forward(request, response);
    	    }

    	    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
    	        dispatcher.forward(request, response);
    	    }

    	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        Persona existingUser = servicio.buscarPersona(id);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
    	        request.setAttribute("usuario", existingUser);
    	        dispatcher.forward(request, response);

    	    }

    	    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String nombre = request.getParameter("nombre");
    	        String direccion = request.getParameter("direccion");
    	        String email = request.getParameter("email");
    	       Persona e=new Persona();  
    	        e.setNombre(nombre);
    	        e.setDireccion(direccion); 
    	        e.setEmail(email);
    	        servicio.insertarPersona(e);
    	        response.sendRedirect("list");
    	    }

    	    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        String nombre = request.getParameter("nombre");
    	        String direccion = request.getParameter("direccion");
    	        String email = request.getParameter("email");
    	        Persona c=new Persona();  
    	        c.setId(id);  
    	        c.setNombre(nombre);
    	        c.setDireccion(direccion); 
    	        c.setEmail(email);
    	        servicio.actualizarPersona(c);
    	        response.sendRedirect("list");
    	    }

    	    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        servicio.eliminarPersona(id);
    	        response.sendRedirect("list");

    	    }
    


	

}
