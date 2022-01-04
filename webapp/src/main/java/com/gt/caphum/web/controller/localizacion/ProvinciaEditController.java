/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\localizacion\ProvinciaEditController.java
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

import com.gt.caphum.web.model.localizacion.Provincia;
import com.gt.caphum.web.service.localizacion.ProvinciaService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ProvinciaEditController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	ProvinciaService provinciaService;

	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	Provincia provincia;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			provincia = provinciaService.getRepo().findById(id).orElseGet(() ->new Provincia());
		} else {
			provincia = new Provincia();
		}
		if (provincia.getId() == null) {
			setDefaultValues(provincia);
		}
	}
	
	private void setDefaultValues(Provincia provincia) {
		provincia.setCodigo(provinciaService.getRepo().nextCodigo());

	}

	public String save() {
		String msg = "Provincia " + (provincia.getNombre()  == null ? " creado exitosamente" : (provincia.getNombre().toString() + " editado exitosamente"));
		String ret = null;
		Severity severity = FacesMessage.SEVERITY_INFO;
		
		try {
			provinciaService.save(provincia);
				
			ret = "ProvinciasList?faces-redirect=true";
		} catch (ValidationException vex) {
			msg = "Error:\n" + vex.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		addDetailMessage(msg, severity);

		return ret;
	}

	public void clear() {
		provincia = new Provincia();
		id = null;
	}

	public boolean isNew() {
		return provincia == null || provincia.getId() == null;
	}

}

