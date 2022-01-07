/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\PuestoEditController.java
 */
package com.gt.caphum.web.controller.rrhh;

import static com.github.adminfaces.template.util.Assert.has;
import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import com.gt.caphum.web.model.rrhh.Puesto;
import com.gt.caphum.web.service.rrhh.PuestoService;

import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PuestoEditController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	PuestoService puestoService;

	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	Puesto puesto;

	@Getter
	@Setter
	List<String> aptitudesRequeridasList = new ArrayList<String>();

	@Getter
	@Setter
	List<String> aptitudesDeseadasList = new ArrayList<String>();

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			puesto = puestoService.getRepo().findById(id).orElseGet(() -> new Puesto());
		} else {
			puesto = new Puesto();
		}
		if (puesto.getId() == null) {
			setDefaultValues(puesto);
		}

		if (puesto.getAptitudesRequeridas() != null && !puesto.getAptitudesRequeridas().isEmpty()) {
			aptitudesRequeridasList.addAll(Arrays.asList(puesto.getAptitudesRequeridas().split(",")));
		}

		if (puesto.getAptitudesDeseadas() != null && !puesto.getAptitudesDeseadas().isEmpty()) {
			aptitudesDeseadasList.addAll(Arrays.asList(puesto.getAptitudesDeseadas().split(",")));
		}
	}

	private void setDefaultValues(Puesto puesto) {
		puesto.setCodigo(puestoService.getRepo().nextCodigo());

	}

	public String save() {
		String msg = "Puesto " + (puesto.getNombre() == null ? " creado exitosamente"
				: (puesto.getNombre().toString() + " editado exitosamente"));
		String ret = null;
		Severity severity = FacesMessage.SEVERITY_INFO;

		try {

			puesto.setAptitudesRequeridas(aptitudesRequeridasList.stream().filter(a -> a != null && !a.trim().isEmpty())
					.map(a -> a.toUpperCase()).sorted()
					.collect(Collectors.joining(", ")));

			puesto.setAptitudesDeseadas(aptitudesDeseadasList.stream().filter(a -> a != null && !a.trim().isEmpty())
					.map(a -> a.toUpperCase()).sorted()
					.collect(Collectors.joining(", ")));

			puestoService.save(puesto);

			ret = "PuestosList?faces-redirect=true";
		} catch (ValidationException vex) {
			msg = "Error:\n" + vex.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		addDetailMessage(msg, severity);

		return ret;
	}

	public void clear() {
		puesto = new Puesto();
		id = null;
	}

	public boolean isNew() {
		return puesto == null || puesto.getId() == null;
	}

}
