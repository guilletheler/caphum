/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\service\rrhh\PersonaService.java
 */
package com.gt.caphum.web.service.rrhh;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.model.rrhh.Persona;
import com.gt.caphum.web.repo.rrhh.DocumentoRepo;
import com.gt.caphum.web.repo.rrhh.PersonaRepo;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.SelectableLazyDMFiller;
import com.gt.toolbox.spb.webapps.commons.infra.service.QueryHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;

@Service
public class PersonaService implements SelectableLazyDMFiller<Persona> {

	@Getter
	@Autowired
	PersonaRepo repo;

	@Autowired
	DocumentoRepo documentoRepo;

	public Page<Persona> findByFilter(Map<String, String> filters, Pageable pageable) {
		return repo.findAll(QueryHelper.getFilterSpecification(filters), pageable);
	}

	@Override
	public Persona findById(Object id) {
		if (id == null || !(id instanceof Integer)) {
			return null;
		}

		return repo.findById((Integer) id).orElseGet(() ->null);
	}
	
	public void save(Persona persona) {
		persona.setUltimaModificacion(new Date());
		repo.save(persona);
	}

	@Transactional
    public void save(Persona persona, List<Documento> documentosEliminados) {

		persona.getDocumentos().removeAll(documentosEliminados);

		for(int i = 0; i < persona.getDocumentos().size(); i++) {
			persona.getDocumentos().get(i).setUltimaModificacion(new Date());
			persona.getDocumentos().set(i, documentoRepo.save(persona.getDocumentos().get(i)));
		}

		this.save(persona);

    }
}

