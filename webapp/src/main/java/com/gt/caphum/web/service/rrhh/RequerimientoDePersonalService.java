/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\service\rrhh\RequerimientoDePersonalService.java
 */
package com.gt.caphum.web.service.rrhh;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gt.caphum.web.model.rrhh.RequerimientoDePersonal;
import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.repo.rrhh.RequerimientoDePersonalRepo;
import com.gt.caphum.web.repo.rrhh.DocumentoRepo;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.SelectableLazyDMFiller;
import com.gt.toolbox.spb.webapps.commons.infra.service.QueryHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
public class RequerimientoDePersonalService implements SelectableLazyDMFiller<RequerimientoDePersonal> {

	@Getter
	@Autowired
	RequerimientoDePersonalRepo repo;
	
	@Autowired
	private DocumentoRepo documentoRepo;

	public Page<RequerimientoDePersonal> findByFilter(Map<String, String> filters, Pageable pageable) {
		return repo.findAll(QueryHelper.getFilterSpecification(filters), pageable);
	}

	@Override
	public RequerimientoDePersonal findById(Object id) {
		if (id == null || !(id instanceof Integer)) {
			return null;
		}

		return repo.findById((Integer) id).orElseGet(() ->null);
	}
	
	public void save(RequerimientoDePersonal requerimientoDePersonal) {
		repo.save(requerimientoDePersonal);
	}

    public void save(RequerimientoDePersonal requerimientoDePersonal, List<Documento> documentosEliminados) {

		requerimientoDePersonal.getDocumentos().removeAll(documentosEliminados);

		for(int i = 0; i < requerimientoDePersonal.getDocumentos().size(); i++) {
			requerimientoDePersonal.getDocumentos().get(i).setUltimaModificacion(new Date());
			requerimientoDePersonal.getDocumentos().set(i, documentoRepo.save(requerimientoDePersonal.getDocumentos().get(i)));
		}

		this.save(requerimientoDePersonal);
    }
}

