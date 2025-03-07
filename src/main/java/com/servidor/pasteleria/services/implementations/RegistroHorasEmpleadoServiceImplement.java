package com.servidor.pasteleria.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servidor.pasteleria.entity.RegistroHorasEmpleadoEntity;
import com.servidor.pasteleria.repo.RegistroHorasEmpleadoRepository;
import com.servidor.pasteleria.services.RegistroHorasEmpleadoService;

@Service
public class RegistroHorasEmpleadoServiceImplement implements RegistroHorasEmpleadoService {

	@Autowired
	RegistroHorasEmpleadoRepository registroHorasEmpleadoRepository;

	@Override
	public List<RegistroHorasEmpleadoEntity> obtenerTodosLosRegistros() {

		return registroHorasEmpleadoRepository.findAll();
	}

}
