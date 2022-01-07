/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\RequerimientoDePersonalEditController.java
 */
package com.gt.caphum.web.controller.rrhh;

import static com.github.adminfaces.template.util.Assert.has;
import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import com.gt.caphum.web.controller.DocumentableHelper;
import com.gt.caphum.web.model.rrhh.RequerimientoDePersonal;
import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.model.rrhh.EstadoRequerimientoDePersonal;
import com.gt.caphum.web.model.rrhh.EstadoEvaluacion;
import com.gt.caphum.web.model.rrhh.Evaluacion;
import com.gt.caphum.web.repo.localizacion.LocalidadRepo;
import com.gt.caphum.web.service.rrhh.RequerimientoDePersonalService;
import com.gt.caphum.web.service.rrhh.DocumentoService;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.CollectionLazyDataModel;

import org.omnifaces.util.Faces;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class RequerimientoDePersonalEditController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	RequerimientoDePersonalService requerimientoDePersonalService;
	
	@Autowired
	private DocumentoService documentoService;
	
	@Autowired
	private LocalidadRepo localidadRepo;

	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	RequerimientoDePersonal requerimientoDePersonal;

	@Getter
	@Setter
	List<Documento> documentosEliminados;

	@Getter
	@Setter
	private DocumentableHelper documentableHelper;
	
	@Getter
	@Setter
	LazyDataModel<Evaluacion> evaluaciones;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			requerimientoDePersonal = requerimientoDePersonalService.getRepo().findById(id).orElseGet(() -> new RequerimientoDePersonal());
		} else {
			requerimientoDePersonal = new RequerimientoDePersonal();
		}
		if (requerimientoDePersonal.getId() == null) {
			setDefaultValues(requerimientoDePersonal);
		}

		requerimientoDePersonal.getDocumentos().size();

		documentableHelper = new DocumentableHelper(requerimientoDePersonal, documentoService.getRepo().nextCodigo());

		evaluaciones = new CollectionLazyDataModel<Evaluacion>(Evaluacion.class, requerimientoDePersonal.getEvaluaciones() , "id");
	}

	private void setDefaultValues(RequerimientoDePersonal requerimientoDePersonal) {
		requerimientoDePersonal.setFechaCreacion(new Date());
		requerimientoDePersonal.setLocalidad(localidadRepo.findTop1By().orElse(null));
		requerimientoDePersonal.setCantidadPuestos(1);
		requerimientoDePersonal.setEstado(EstadoRequerimientoDePersonal.PLANIFICACION);
		requerimientoDePersonal.setInicio(new Date());
	}

	public String save() {
		String msg = "RequerimientoDePersonal " + (requerimientoDePersonal.getId() == null ? " creado exitosamente"
				: (requerimientoDePersonal.getId().toString() + " editado exitosamente"));
		String ret = null;
		Severity severity = FacesMessage.SEVERITY_INFO;

		try {
			requerimientoDePersonalService.save(requerimientoDePersonal, documentableHelper.getDocumentosEliminados());

			ret = "RequerimientoDePersonalesList?faces-redirect=true";
		} catch (ValidationException vex) {
			msg = "Error:\n" + vex.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		addDetailMessage(msg, severity);

		return ret;
	}

	public void clear() {
		requerimientoDePersonal = new RequerimientoDePersonal();
		id = null;
	}

	public boolean isNew() {
		return requerimientoDePersonal == null || requerimientoDePersonal.getId() == null;
	}

	public void crearEvaluacion() {
		Evaluacion evaluacion = new Evaluacion();
		evaluacion.setRequerimientoDePersonal(requerimientoDePersonal);
		evaluacion.setEstado(EstadoEvaluacion.PLANIFICACION);

		requerimientoDePersonal.getEvaluaciones().add(evaluacion);
	}

}
