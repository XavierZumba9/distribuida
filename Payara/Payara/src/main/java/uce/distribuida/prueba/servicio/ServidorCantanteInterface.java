package uce.distribuida.prueba.servicio;

import java.util.List;

import uce.distribuida.prueba.pojo.Cantante;

public interface ServidorCantanteInterface {


	 void ingresarCantante(Cantante cantante);
	 Cantante buscarCantantePorId(int id);
	 void actualizarCantante(Cantante cantante);
	 List<Cantante> mostrarTodos();
	 void eliminarCantante (int id);
}
