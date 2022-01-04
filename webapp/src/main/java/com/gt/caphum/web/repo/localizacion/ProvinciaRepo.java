/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\repo\localizacion\ProvinciaRepo.java
 */
package com.gt.caphum.web.repo.localizacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gt.caphum.web.model.localizacion.Provincia;

@Repository
public interface ProvinciaRepo extends PagingAndSortingRepository<Provincia, Integer>,
		JpaSpecificationExecutor<Provincia> {

	@Query("SELECT COALESCE(MAX(entity.codigo), 0) + 1 FROM Provincia entity")
Integer nextCodigo();
	
Optional<Provincia> findByCodigo(Integer codigo);

}


