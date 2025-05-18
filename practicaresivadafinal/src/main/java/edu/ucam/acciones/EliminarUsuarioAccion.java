package edu.ucam.acciones;

import java.util.HashSet;

import edu.ucam.dao.UsuarioDAO;
import edu.ucam.domain.Usuario;
import edu.ucam.interfaces.Accion;
import edu.ucam.util.Constantes;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EliminarUsuarioAccion implements Accion {

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServletContext context = request.getServletContext();
		UsuarioDAO usuarioDAO = (UsuarioDAO) context.getAttribute("USUARIODAO");
		
		String nombre = (String) request.getParameter(Constantes.PARAM_NOMBRE);
		
		usuarioDAO.eliminar(nombre);
		
		HashSet<Usuario> listaUsuarios = usuarioDAO.obtenerTodos();
		
		context.setAttribute("LISTA_USUARIOS", listaUsuarios);
		
		request.setAttribute("exito_eliminar", "Usuario eliminado con exito");
		
		return "/secured/admin/usermanagement.jsp";
	}

}
