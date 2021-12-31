/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\DocumentoListController.java
 */
package com.gt.caphum.web.controller.rrhh;

import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.repo.rrhh.DocumentoRepo;
import com.gt.caphum.web.service.rrhh.DocumentoService;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.EntityLazyDataModel;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import lombok.Getter;

@Named
@ViewScoped
public class DocumentoListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	DocumentoRepo documentoRepo;
	
	@Inject
	DocumentoService documentoService;

	@Getter
	LazyDataModel<Documento> lazyDataModel;
	
	@PostConstruct
	private void init() {
		lazyDataModel = new EntityLazyDataModel<>(documentoService, Documento.class, "id");
	}

	public void borrarDocumento(Documento documento) {
		documentoRepo.delete(documento);
		addDetailMessage("Documento " + documento.getNombre() + " borrado exitosamente");
	}
	
	public SortMeta getDefaultSortBy() {
		return SortMeta.builder().field("id").order(SortOrder.ASCENDING).build();
	}
}

