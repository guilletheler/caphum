/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\DocumentoEditController.java
 */
package com.gt.caphum.web.controller.rrhh;

import static com.github.adminfaces.template.util.Assert.has;
import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.service.rrhh.DocumentoService;

import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class DocumentoEditController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	DocumentoService documentoService;

	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	Documento documento;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			documento = documentoService.getRepo().findById(id).orElse(new Documento());
		} else {
			documento = new Documento();
		}
		if (documento.getId() == null) {
			setDefaultValues(documento);
		}
	}

	private void setDefaultValues(Documento documento) {
		documento.setCodigo(documentoService.getRepo().nextCodigo());

	}

	public String save() {
		String msg = "Documento " + (documento.getNombre() == null ? " creado exitosamente"
				: (documento.getNombre().toString() + " editado exitosamente"));
		String ret = null;
		Severity severity = FacesMessage.SEVERITY_INFO;

		try {
			documentoService.save(documento);

			ret = "DocumentosList?faces-redirect=true";
		} catch (ValidationException vex) {
			msg = "Error:\n" + vex.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		addDetailMessage(msg, severity);

		return ret;
	}

	public void clear() {
		documento = new Documento();
		id = null;
	}

	public boolean isNew() {
		return documento == null || documento.getId() == null;
	}

}
