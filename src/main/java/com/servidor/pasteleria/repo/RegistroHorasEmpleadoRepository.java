package com.servidor.pasteleria.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import com.servidor.pasteleria.entity.RegistroHorasEmpleadoEntity;

@Repository
public interface RegistroHorasEmpleadoRepository extends JpaRepository<RegistroHorasEmpleadoEntity, Long> {
	// JpaRepository ya incluye métodos para CRUD:
	// save() - Crear/Actualizar
	// findById() - Leer por ID
	// findAll() - Leer todos
	// deleteById() - Eliminar por ID
	
	
	@Query("SELECT rgs FROM RegistroHorasEmpleadoEntity rgs WHERE rgs.empleado.idEmpleado = :empleadoId AND rgs.horaSalida IS NULL ORDER BY rgs.horaEntrada DESC LIMIT 1")
	RegistroHorasEmpleadoEntity buscarUltimoRegistroSinSalida(@Param("empleadoId") Long empleadoId);
	
	
	@Query("SELECT rgs FROM RegistroHorasEmpleadoEntity rgs WHERE rgs.empleado.idEmpleado = :empleadoId ORDER BY rgs.horaEntrada DESC LIMIT 1")
	RegistroHorasEmpleadoEntity buscarUltimoRegistro(@Param("empleadoId") Long empleadoId);
	

}


