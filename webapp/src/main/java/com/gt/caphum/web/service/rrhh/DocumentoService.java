/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\service\rrhh\DocumentoService.java
 */
package com.gt.caphum.web.service.rrhh;

import java.util.Date;
import java.util.Map;

import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.repo.rrhh.DocumentoRepo;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.SelectableLazyDMFiller;
import com.gt.toolbox.spb.webapps.commons.infra.service.QueryHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
public class DocumentoService implements SelectableLazyDMFiller<Documento> {

	@Getter
	@Autowired
	DocumentoRepo repo;

	public Page<Documento> findByFilter(Map<String, String> filters, Pageable pageable) {
		return repo.findAll(QueryHelper.getFilterSpecification(filters), pageable);
	}

	@Override
	public Documento findById(Object id) {
		if (id == null || !(id instanceof Integer)) {
			return null;
		}

		return repo.findById((Integer) id).orElse(null);
	}
	
	public void save(Documento documento) {
		documento.setUltimaModificacion(new Date());
		repo.save(documento);
	}
}

