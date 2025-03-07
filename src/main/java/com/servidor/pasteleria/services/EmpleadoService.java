package com.servidor.pasteleria.services;
import java.util.List;

import com.servidor.pasteleria.entity.EmpleadoEntity;
import com.servidor.pasteleria.model.EmpleadoDTO;

public interface EmpleadoService {
	
	EmpleadoEntity buscarPorUsuario (String usuario);
	List<EmpleadoDTO> obtenerTodosLosEmpleados();
	void guardarEmpleado(EmpleadoDTO empleado);
	void comenzarJornada(EmpleadoEntity empleado);
	void finalizarJornada(EmpleadoEntity empleado);
	boolean estaTrabajando(EmpleadoEntity empleado);
}
