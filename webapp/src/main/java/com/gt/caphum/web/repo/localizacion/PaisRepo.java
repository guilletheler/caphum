/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\repo\localizacion\PaisRepo.java
 */
package com.gt.caphum.web.repo.localizacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gt.caphum.web.model.localizacion.Pais;

@Repository
public interface PaisRepo extends PagingAndSortingRepository<Pais, Integer>,
		JpaSpecificationExecutor<Pais> {

	@Query("SELECT COALESCE(MAX(entity.codigo), 0) + 1 FROM Pais entity")
Integer nextCodigo();
	
Optional<Pais> findByCodigo(Integer codigo);

}


