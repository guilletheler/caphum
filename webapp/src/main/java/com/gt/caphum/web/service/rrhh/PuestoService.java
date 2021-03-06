/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\service\rrhh\PuestoService.java
 */
package com.gt.caphum.web.service.rrhh;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gt.toolbox.spb.webapps.commons.infra.datamodel.SelectableLazyDMFiller;
import com.gt.caphum.web.model.rrhh.Puesto;
import com.gt.caphum.web.repo.rrhh.PuestoRepo;
import com.gt.toolbox.spb.webapps.commons.infra.service.QueryHelper;

import lombok.Getter;

@Service
public class PuestoService implements SelectableLazyDMFiller<Puesto> {

	@Getter
	@Autowired
	PuestoRepo repo;

	public Page<Puesto> findByFilter(Map<String, String> filters, Pageable pageable) {
		return repo.findAll(QueryHelper.getFilterSpecification(filters), pageable);
	}

	@Override
	public Puesto findById(Object id) {
		if (id == null || !(id instanceof Integer)) {
			return null;
		}

		return repo.findById((Integer) id).orElseGet(() ->null);
	}
	
	public void save(Puesto puesto) {
		repo.save(puesto);
	}
}

