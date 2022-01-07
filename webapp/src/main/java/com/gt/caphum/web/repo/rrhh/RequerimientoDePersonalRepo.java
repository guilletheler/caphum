/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\repo\rrhh\RequerimientoDePersonalRepo.java
 */
package com.gt.caphum.web.repo.rrhh;

import com.gt.caphum.web.model.rrhh.RequerimientoDePersonal;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequerimientoDePersonalRepo extends PagingAndSortingRepository<RequerimientoDePersonal, Integer>,
		JpaSpecificationExecutor<RequerimientoDePersonal> {

	
}


