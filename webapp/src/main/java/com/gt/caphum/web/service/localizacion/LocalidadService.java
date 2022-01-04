/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\service\localizacion\LocalidadService.java
 */
package com.gt.caphum.web.service.localizacion;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gt.toolbox.spb.webapps.commons.infra.datamodel.SelectableLazyDMFiller;
import com.gt.caphum.web.model.localizacion.Localidad;
import com.gt.caphum.web.repo.localizacion.LocalidadRepo;
import com.gt.caphum.web.service.QueryHelper;

import lombok.Getter;

@Service
public class LocalidadService implements SelectableLazyDMFiller<Localidad> {

	@Getter
	@Autowired
	LocalidadRepo repo;

	public Page<Localidad> findByFilter(Map<String, String> filters, Pageable pageable) {
		return repo.findAll(QueryHelper.getFilterSpecification(filters), pageable);
	}

	@Override
	public Localidad findById(Object id) {
		if (id == null || !(id instanceof Integer)) {
			return null;
		}

		return repo.findById((Integer) id).orElseGet(() ->null);
	}
	
	public void save(Localidad localidad) {
		repo.save(localidad);
	}
}

