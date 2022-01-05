/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\PersonaEditController.java
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

import com.gt.caphum.web.model.rrhh.Persona;
import com.gt.caphum.web.service.rrhh.PersonaService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PersonaEditController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	PersonaService personaService;

	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	Persona persona;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			persona = personaService.getRepo().findById(id).orElseGet(() ->new Persona());
		} else {
			persona = new Persona();
		}
		if (persona.getId() == null) {
			setDefaultValues(persona);
		}
	}
	
	private void setDefaultValues(Persona persona) {
		persona.setCodigo(personaService.getRepo().nextCodigo());

	}

	public String save() {
		String msg = "Persona " + (persona.getNombre()  == null ? " creado exitosamente" : (persona.getNombre().toString() + " editado exitosamente"));
		String ret = null;
		Severity severity = FacesMessage.SEVERITY_INFO;
		
		try {
			personaService.save(persona);
				
			ret = "PersonasList?faces-redirect=true";
		} catch (ValidationException vex) {
			msg = "Error:\n" + vex.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		addDetailMessage(msg, severity);

		return ret;
	}

	public void clear() {
		persona = new Persona();
		id = null;
	}

	public boolean isNew() {
		return persona == null || persona.getId() == null;
	}

}

