/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\RequerimientoDePersonalListController.java
 */
package com.gt.caphum.web.controller.rrhh;

import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.gt.caphum.web.model.rrhh.RequerimientoDePersonal;
import com.gt.caphum.web.service.rrhh.RequerimientoDePersonalService;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.EntityLazyDataModel;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import lombok.Getter;

@Named
@ViewScoped
public class RequerimientoDePersonalListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	RequerimientoDePersonalService requerimientoDePersonalService;

	@Getter
	LazyDataModel<RequerimientoDePersonal> lazyDataModel;
	
	@PostConstruct
	private void init() {
		lazyDataModel = new EntityLazyDataModel<>(requerimientoDePersonalService, RequerimientoDePersonal.class, "id");
	}

	public void borrarRequerimientoDePersonal(RequerimientoDePersonal requerimientoDePersonal) {
		requerimientoDePersonalService.getRepo().delete(requerimientoDePersonal);
		addDetailMessage("RequerimientoDePersonal " + requerimientoDePersonal.getId() + " borrado exitosamente");
	}
	
	public SortMeta getDefaultSortBy() {
		return SortMeta.builder().field("finalizacion").order(SortOrder.DESCENDING).build();
	}
}

