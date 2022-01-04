/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\localizacion\PaisEditController.java
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

import com.gt.caphum.web.model.localizacion.Pais;
import com.gt.caphum.web.service.localizacion.PaisService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PaisEditController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	PaisService paisService;

	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	Pais pais;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			pais = paisService.getRepo().findById(id).orElseGet(() ->new Pais());
		} else {
			pais = new Pais();
		}
		if (pais.getId() == null) {
			setDefaultValues(pais);
		}
	}
	
	private void setDefaultValues(Pais pais) {
		pais.setCodigo(paisService.getRepo().nextCodigo());

	}

	public String save() {
		String msg = "Pais " + (pais.getNombre()  == null ? " creado exitosamente" : (pais.getNombre().toString() + " editado exitosamente"));
		String ret = null;
		Severity severity = FacesMessage.SEVERITY_INFO;
		
		try {
			paisService.save(pais);
				
			ret = "PaisesList?faces-redirect=true";
		} catch (ValidationException vex) {
			msg = "Error:\n" + vex.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		addDetailMessage(msg, severity);

		return ret;
	}

	public void clear() {
		pais = new Pais();
		id = null;
	}

	public boolean isNew() {
		return pais == null || pais.getId() == null;
	}

}

