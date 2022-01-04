/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\service\localizacion\PaisService.java
 */
package com.gt.caphum.web.service.localizacion;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gt.toolbox.spb.webapps.commons.infra.datamodel.SelectableLazyDMFiller;
import com.gt.caphum.web.model.localizacion.Pais;
import com.gt.caphum.web.repo.localizacion.LocalidadRepo;
import com.gt.caphum.web.repo.localizacion.PaisRepo;
import com.gt.caphum.web.repo.localizacion.ProvinciaRepo;
import com.gt.caphum.web.service.QueryHelper;

import lombok.Getter;

@Service
public class PaisService implements SelectableLazyDMFiller<Pais> {

	@Getter
	@Autowired
	PaisRepo repo;

	@Autowired
	ProvinciaRepo provinciaRepo;

	@Autowired
	LocalidadRepo localidadRepo;

	public Page<Pais> findByFilter(Map<String, String> filters, Pageable pageable) {
		return repo.findAll(QueryHelper.getFilterSpecification(filters), pageable);
	}

	@Override
	public Pais findById(Object id) {
		if (id == null || !(id instanceof Integer)) {
			return null;
		}

		return repo.findById((Integer) id).orElseGet(() ->null);
	}
	
	public void save(Pais pais) {
		repo.save(pais);
	}
}

