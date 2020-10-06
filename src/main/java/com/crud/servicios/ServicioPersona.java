package com.crud.servicios;

import java.util.List;

import com.crud.persona.Persona;

public interface ServicioPersona {
	
	public void insertarPersona(Persona user);
	
	public Persona buscarPersona(int id);
	
	public List<Persona> buscarTodos();
	
	public int eliminarPersona(int id);
	
	public int actualizarPersona(Persona user);

}
