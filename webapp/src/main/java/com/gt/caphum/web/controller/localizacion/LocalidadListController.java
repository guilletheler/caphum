/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\localizacion\LocalidadListController.java
 */
package com.gt.caphum.web.controller.localizacion;

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

import com.gt.caphum.web.model.localizacion.Localidad;
import com.gt.caphum.web.repo.localizacion.LocalidadRepo;
import com.gt.caphum.web.service.localizacion.LocalidadService;

import lombok.Getter;

@Named
@ViewScoped
public class LocalidadListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	LocalidadRepo localidadRepo;
	
	@Inject
	LocalidadService localidadService;

	@Getter
	LazyDataModel<Localidad> lazyDataModel;
	
	@PostConstruct
	private void init() {
		lazyDataModel = new EntityLazyDataModel<>(localidadService, Localidad.class, "id");
	}

	public void borrarLocalidad(Localidad localidad) {
		localidadRepo.delete(localidad);
		addDetailMessage("Localidad " + localidad.getNombre() + " borrado exitosamente");
	}
	
	public SortMeta getDefaultSortBy() {
		return SortMeta.builder().field("id").order(SortOrder.ASCENDING).build();
	}
}

