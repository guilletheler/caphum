/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\repo\rrhh\BusquedaRepo.java
 */
package com.gt.caphum.web.repo.rrhh;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gt.caphum.web.model.rrhh.Busqueda;

@Repository
public interface BusquedaRepo extends PagingAndSortingRepository<Busqueda, Integer>,
		JpaSpecificationExecutor<Busqueda> {

	
}


