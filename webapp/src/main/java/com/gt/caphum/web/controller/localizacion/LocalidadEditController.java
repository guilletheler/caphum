/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\localizacion\LocalidadEditController.java
 */
package com.gt.caphum.web.controller.localizacion;

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

import com.gt.caphum.web.model.localizacion.Localidad;
import com.gt.caphum.web.service.localizacion.LocalidadService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class LocalidadEditController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	LocalidadService localidadService;

	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	Localidad localidad;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			localidad = localidadService.getRepo().findById(id).orElseGet(() ->new Localidad());
		} else {
			localidad = new Localidad();
		}
		if (localidad.getId() == null) {
			setDefaultValues(localidad);
		}
	}
	
	private void setDefaultValues(Localidad localidad) {
		localidad.setCodigo(localidadService.getRepo().nextCodigo());

	}

	public String save() {
		String msg = "Localidad " + (localidad.getNombre()  == null ? " creado exitosamente" : (localidad.getNombre().toString() + " editado exitosamente"));
		String ret = null;
		Severity severity = FacesMessage.SEVERITY_INFO;
		
		try {
			localidadService.save(localidad);
				
			ret = "LocalidadesList?faces-redirect=true";
		} catch (ValidationException vex) {
			msg = "Error:\n" + vex.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		addDetailMessage(msg, severity);

		return ret;
	}

	public void clear() {
		localidad = new Localidad();
		id = null;
	}

	public boolean isNew() {
		return localidad == null || localidad.getId() == null;
	}

}

