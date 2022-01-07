/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\rrhh\DocumentoListController.java
 */
package com.gt.caphum.web.controller.rrhh;

import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.service.rrhh.DocumentoService;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.EntityLazyDataModel;

import org.apache.commons.lang3.ArrayUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;

import lombok.Getter;

@Named
@ViewScoped
public class DocumentoListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	DocumentoService documentoService;

	@Getter
	LazyDataModel<Documento> lazyDataModel;

	@PostConstruct
	private void init() {
		lazyDataModel = new EntityLazyDataModel<>(documentoService, Documento.class, "id");
	}

	public void crearDocumento() {
		Documento documento = new Documento();
		documento.setNombre("Nuevo Documento");
		documento.setCodigo(documentoService.getRepo().nextCodigo());
		documento.setFechaCreacion(new Date());

		documentoService.save(documento);
		lazyDataModel = new EntityLazyDataModel<>(documentoService, Documento.class, "id");
	}

	public void borrarDocumento(Documento documento) {
		documentoService.getRepo().delete(documento);
		addDetailMessage("Documento " + documento.getNombre() + " borrado exitosamente");
	}

	public SortMeta getDefaultSortBy() {
		return SortMeta.builder().field("codigo").order(SortOrder.ASCENDING).build();
	}

	public StreamedContent buildContent(Documento documento) {

		Documento doc = documentoService.getRepo().findById(documento.getId()).orElse(null);

		String type = "";

		if (doc.getFileName().toLowerCase().trim().endsWith(".pdf")) {
			type = "application/pdf";
		} else if (doc.getFileName().toLowerCase().trim().endsWith(".xls")
				|| doc.getFileName().toLowerCase().trim().endsWith(".xlsx")) {
			type = "application/vnd.ms-excel";
		} else if (doc.getFileName().toLowerCase().trim().endsWith(".png")) {
			type = "image/png";
		} else if (doc.getFileName().toLowerCase().trim().endsWith(".jpg")
				|| doc.getFileName().toLowerCase().trim().endsWith(".jpeg")) {
			type = "image/jpeg";
		} else if (doc.getFileName().toLowerCase().trim().endsWith(".doc")
				|| doc.getFileName().toLowerCase().trim().endsWith(".docx")) {
			type = "application/msword";
		}

		return DefaultStreamedContent.builder()
				.stream(() -> new ByteArrayInputStream(ArrayUtils.toPrimitive(doc.getContenido())))
				.contentType(type).build();
	}

	public void onRowEdit(RowEditEvent<Documento> event) {

		Documento documento = event.getObject();

		String msg = "Documento " + (documento.getNombre() == null ? " creado exitosamente"
				: (documento.getNombre().toString() + " editado exitosamente"));
		Severity severity = FacesMessage.SEVERITY_INFO;

		try {
			documentoService.save(documento);
		} catch (ValidationException vex) {
			msg = "Error:\n" + vex.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		addDetailMessage(msg, severity);
	}

	public void onRowCancel(RowEditEvent<Documento> event) {
		FacesMessage msg = new FacesMessage("Edición cancelada código ", String.valueOf(event.getObject().getCodigo()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
