/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\localizacion\ProvinciaListController.java
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

import com.gt.caphum.web.model.localizacion.Provincia;
import com.gt.caphum.web.repo.localizacion.ProvinciaRepo;
import com.gt.caphum.web.service.localizacion.ProvinciaService;

import lombok.Getter;

@Named
@ViewScoped
public class ProvinciaListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	ProvinciaRepo provinciaRepo;
	
	@Inject
	ProvinciaService provinciaService;

	@Getter
	LazyDataModel<Provincia> lazyDataModel;
	
	@PostConstruct
	private void init() {
		lazyDataModel = new EntityLazyDataModel<>(provinciaService, Provincia.class, "id");
	}

	public void borrarProvincia(Provincia provincia) {
		provinciaRepo.delete(provincia);
		addDetailMessage("Provincia " + provincia.getNombre() + " borrado exitosamente");
	}
	
	public SortMeta getDefaultSortBy() {
		return SortMeta.builder().field("id").order(SortOrder.ASCENDING).build();
	}
}

