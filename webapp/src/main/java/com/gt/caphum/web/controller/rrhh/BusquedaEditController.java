/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\BusquedaEditController.java
 */
package com.gt.caphum.web.controller.rrhh;

import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import org.omnifaces.util.Faces;

import com.gt.caphum.web.model.rrhh.Busqueda;
import com.gt.caphum.web.service.rrhh.BusquedaService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class BusquedaEditController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	BusquedaService busquedaService;

	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	Busqueda busqueda;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			busqueda = busquedaService.getRepo().findById(id).orElseGet(() ->new Busqueda());
		} else {
			busqueda = new Busqueda();
		}
		if (busqueda.getId() == null) {
			setDefaultValues(busqueda);
		}
	}
	
	private void setDefaultValues(Busqueda busqueda) {
		
	}

	public String save() {
		String msg = "Busqueda " + (busqueda.getId()  == null ? " creado exitosamente" : (busqueda.getId().toString() + " editado exitosamente"));
		String ret = null;
		Severity severity = FacesMessage.SEVERITY_INFO;
		
		try {
			busquedaService.save(busqueda);
				
			ret = "BusquedasList?faces-redirect=true";
		} catch (ValidationException vex) {
			msg = "Error:\n" + vex.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		addDetailMessage(msg, severity);

		return ret;
	}

	public void clear() {
		busqueda = new Busqueda();
		id = null;
	}

	public boolean isNew() {
		return busqueda == null || busqueda.getId() == null;
	}

}

