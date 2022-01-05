/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\repo\rrhh\PuestoRepo.java
 */
package com.gt.caphum.web.repo.rrhh;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gt.caphum.web.model.rrhh.Puesto;

@Repository
public interface PuestoRepo extends PagingAndSortingRepository<Puesto, Integer>,
		JpaSpecificationExecutor<Puesto> {

	@Query("SELECT COALESCE(MAX(entity.codigo), 0) + 1 FROM Puesto entity")
Integer nextCodigo();
	
Optional<Puesto> findByCodigo(Integer codigo);

}


