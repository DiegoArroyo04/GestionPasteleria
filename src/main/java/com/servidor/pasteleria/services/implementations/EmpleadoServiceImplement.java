package com.servidor.pasteleria.services.implementations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servidor.pasteleria.entity.EmpleadoEntity;
import com.servidor.pasteleria.entity.RegistroHorasEmpleadoEntity;
import com.servidor.pasteleria.model.EmpleadoDTO;
import com.servidor.pasteleria.repo.EmpleadoRepository;
import com.servidor.pasteleria.repo.RegistroHorasEmpleadoRepository;
import com.servidor.pasteleria.services.EmpleadoService;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	RegistroHorasEmpleadoRepository registroHorasEmpleadoRepository;

	@Override
	public EmpleadoEntity buscarPorUsuario(String usuario) {
		EmpleadoEntity empleadoEntity = null;
		empleadoEntity = empleadoRepository.buscarPorUsuario(usuario);

		return empleadoEntity;
	}

	@Override
	public List<EmpleadoDTO> obtenerTodosLosEmpleados() {
		List<EmpleadoEntity> empleadosEntity = empleadoRepository.findAll();
		List<EmpleadoDTO> empleados = new ArrayList<EmpleadoDTO>();
		// CONVERTIR A DTO
		for (int i = 0; i < empleadosEntity.size(); i++) {
			EmpleadoDTO empleado = new EmpleadoDTO(empleadosEntity.get(i).getIdEmpleado(),
					empleadosEntity.get(i).getNombre(), empleadosEntity.get(i).getApellidos(),
					empleadosEntity.get(i).getTelefono(), empleadosEntity.get(i).getFechaContratacion(),
					empleadosEntity.get(i).getRol(), empleadosEntity.get(i).getUsuario(),
					empleadosEntity.get(i).getContrasenia());
			empleados.add(empleado);

		}
		return empleados;
	}

	@Override
	public void guardarEmpleado(EmpleadoDTO empleado) {
		//CONVERSION A ENTITY
		EmpleadoEntity empleadoEntity=new EmpleadoEntity();
		
		empleadoEntity.setNombre(empleado.getNombre());
		empleadoEntity.setApellidos(empleado.getApellidos());
		empleadoEntity.setTelefono(empleado.getTelefono());
		empleadoEntity.setRol(empleado.getRol());
		empleadoEntity.setUsuario(empleado.getUsuario());
		empleadoEntity.setContrasenia(empleado.getContrasenia());
		
		empleadoRepository.save(empleadoEntity);
		
		
	}

	@Override
	public void comenzarJornada(EmpleadoEntity empleado) {
		
		RegistroHorasEmpleadoEntity nuevoRegistro=new RegistroHorasEmpleadoEntity();
		
		nuevoRegistro.setEmpleado(empleado);
		
		registroHorasEmpleadoRepository.save(nuevoRegistro);
		
		
	}

	@Override
	public void finalizarJornada(EmpleadoEntity empleado) {

		RegistroHorasEmpleadoEntity ultimoRegistro = registroHorasEmpleadoRepository.buscarUltimoRegistroSinSalida(empleado.getIdEmpleado());

		    //REGISTRAR HORA DE SALIDA
		    
		    // Obtener la hora actual
	        LocalDateTime horaActual = LocalDateTime.now();
	       
	        // Convertir LocalDateTime a Timestamp
	        Timestamp timestampHoraSalida = Timestamp.valueOf(horaActual);
		    
		    ultimoRegistro.setHoraSalida(timestampHoraSalida);
		    registroHorasEmpleadoRepository.save(ultimoRegistro);
		
	}

	@Override
	public boolean estaTrabajando(EmpleadoEntity empleado) {
		//OBTENER ULTIMO REGISTRO PARA VERIFICAR SU HORA DE SALIDA
		 RegistroHorasEmpleadoEntity ultimoRegistro = registroHorasEmpleadoRepository.buscarUltimoRegistro(empleado.getIdEmpleado());

		 if(ultimoRegistro!=null) {
			 //SI NO TIENE REGISTRADA HORA DE SALIDA ESTA TRABAJANDO
			 if(ultimoRegistro.getHoraSalida()==null) {
				 return true;
			 }else {
				 return false;
			 }
			 
		 }else {
			 return false;
		 }
		
		
	}

}
