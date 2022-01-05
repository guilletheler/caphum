/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\BusquedaListController.java
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

import com.gt.caphum.web.model.rrhh.Busqueda;
import com.gt.caphum.web.repo.rrhh.BusquedaRepo;
import com.gt.caphum.web.service.rrhh.BusquedaService;

import lombok.Getter;

@Named
@ViewScoped
public class BusquedaListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	BusquedaService busquedaService;

	@Getter
	LazyDataModel<Busqueda> lazyDataModel;
	
	@PostConstruct
	private void init() {
		lazyDataModel = new EntityLazyDataModel<>(busquedaService, Busqueda.class, "id");
	}

	public void borrarBusqueda(Busqueda busqueda) {
		busquedaService.getRepo().delete(busqueda);
		addDetailMessage("Busqueda " + busqueda.getId() + " borrado exitosamente");
	}
	
	public SortMeta getDefaultSortBy() {
		return SortMeta.builder().field("id").order(SortOrder.ASCENDING).build();
	}
}

