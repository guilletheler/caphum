/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\RequerimientoDePersonalEditController.java
 */
package com.gt.caphum.web.controller.rrhh;

import static com.github.adminfaces.template.util.Assert.has;
import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import com.gt.caphum.web.controller.DocumentableHelper;
import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.model.rrhh.EstadoEvaluacion;
import com.gt.caphum.web.model.rrhh.EstadoRequerimientoDePersonal;
import com.gt.caphum.web.model.rrhh.Evaluacion;
import com.gt.caphum.web.model.rrhh.Persona;
import com.gt.caphum.web.model.rrhh.RequerimientoDePersonal;
import com.gt.caphum.web.repo.localizacion.LocalidadRepo;
import com.gt.caphum.web.service.rrhh.DocumentoService;
import com.gt.caphum.web.service.rrhh.PersonaService;
import com.gt.caphum.web.service.rrhh.RequerimientoDePersonalService;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.CollectionLazyDataModel;

import org.hibernate.LazyInitializationException;
import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.Visibility;
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
	private PersonaService personaService;

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
	Map<Evaluacion, List<Documento>> documentosDeEvaluacionesEliminados;

	@Getter
	@Setter
	private DocumentableHelper documentableHelper;

	@Getter
	private DocumentableHelper evaluacionDocumentableHelper;

	@Getter
	private DocumentableHelper personaDocumentableHelper;

	@Getter
	@Setter
	LazyDataModel<Evaluacion> evaluaciones;

	public void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}
		if (has(id)) {
			requerimientoDePersonal = requerimientoDePersonalService.getRepo().findById(id)
					.orElseGet(() -> new RequerimientoDePersonal());
		} else {
			requerimientoDePersonal = new RequerimientoDePersonal();
		}
		if (requerimientoDePersonal.getId() == null) {
			setDefaultValues(requerimientoDePersonal);
		}

		requerimientoDePersonal.getDocumentos().size();

		documentableHelper = DocumentableHelper.builder().documentable(requerimientoDePersonal)
				.nextCodigo(documentoService.getRepo().nextCodigo()).build();

		requerimientoDePersonal.getEvaluaciones().forEach(e -> e.getDocumentos().size());

		evaluaciones = new CollectionLazyDataModel<Evaluacion>(Evaluacion.class,
				requerimientoDePersonal.getEvaluaciones(), "id");

		documentosDeEvaluacionesEliminados = new HashMap<>();
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
			requerimientoDePersonalService.save(requerimientoDePersonal, documentableHelper.getDocumentosEliminados(), documentosDeEvaluacionesEliminados);

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

	public void onEvaluacionEdited(SelectEvent<Evaluacion> event) {
		Evaluacion evaluacion = event.getObject();
		if (evaluacion != null) {
			if (evaluacion.getRequerimientoDePersonal() == null) {
				evaluacion.setRequerimientoDePersonal(requerimientoDePersonal);
				requerimientoDePersonal.getEvaluaciones().add(evaluacion);
			}
		}
	}

	public void eliminarEvaluacion(Evaluacion evaluacion) {
		requerimientoDePersonal.getEvaluaciones().remove(evaluacion);
	}

	public void onEvaluacionesTableRowToggle(ToggleEvent event) {

		if (event.getVisibility() == Visibility.VISIBLE) {
			Evaluacion evaluacion = (Evaluacion) event.getData();
			if (evaluacion != null) {

				evaluacionDocumentableHelper = DocumentableHelper.builder().documentable(evaluacion)
						.codeGen(documentableHelper).build();
				if (evaluacion.getPersona() != null) {
					Persona per = evaluacion.getPersona();
					try {
						per.getDocumentos().size();
					} catch (LazyInitializationException ex) {
						per = personaService.getRepo().findById(per.getId()).orElse(null);
						per.getDocumentos().size();
					}
					personaDocumentableHelper = DocumentableHelper.builder()
							.documentable(per)
							.codeGen(documentableHelper)
							.readOnly(true)
							.build();
				}
			}
		}
	}

	public void finalizaEdicionEvaluacion(Evaluacion item) {

		if(!documentosDeEvaluacionesEliminados.containsKey(item)) {
			documentosDeEvaluacionesEliminados.put(item, new ArrayList<>());
		}

		documentosDeEvaluacionesEliminados.get(item).addAll(evaluacionDocumentableHelper.getDocumentosEliminados());
	}
}
