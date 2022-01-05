/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\PuestoListController.java
 */
package com.gt.caphum.web.controller.rrhh;

import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.model.LazyDataModel;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.EntityLazyDataModel;

import com.gt.caphum.web.model.rrhh.Puesto;
import com.gt.caphum.web.repo.rrhh.PuestoRepo;
import com.gt.caphum.web.service.rrhh.PuestoService;

import lombok.Getter;

@Named
@ViewScoped
public class PuestoListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	PuestoService puestoService;

	@Getter
	LazyDataModel<Puesto> lazyDataModel;
	
	@PostConstruct
	private void init() {
		lazyDataModel = new EntityLazyDataModel<>(puestoService, Puesto.class, "id");
	}

	public void borrarPuesto(Puesto puesto) {
		puestoService.getRepo().delete(puesto);
		addDetailMessage("Puesto " + puesto.getNombre() + " borrado exitosamente");
	}
	
	public SortMeta getDefaultSortBy() {
		return SortMeta.builder().field("id").order(SortOrder.ASCENDING).build();
	}
}

