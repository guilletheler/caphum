/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\repo\localizacion\LocalidadRepo.java
 */
package com.gt.caphum.web.repo.localizacion;

import java.util.Optional;

import com.gt.caphum.web.model.localizacion.Localidad;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepo extends PagingAndSortingRepository<Localidad, Integer>,
		JpaSpecificationExecutor<Localidad> {

	@Query("SELECT COALESCE(MAX(entity.codigo), 0) + 1 FROM Localidad entity")
	Integer nextCodigo();

	Optional<Localidad> findByCodigo(Integer codigo);

	Optional<Localidad> findTop1By();

}
