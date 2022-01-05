package com.gt.caphum.web.model.rrhh;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gt.caphum.web.model.CodigoNombre;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithObservaciones;

import org.apache.commons.lang3.ArrayUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "documentos")
public class Documento extends CodigoNombre implements IWithIntegerId, IWithObservaciones, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String fileName;

    @Temporal(TemporalType.DATE)
    Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    Date fechaVencimiento;

    @Temporal(TemporalType.DATE)
    Date fechaEliminacion;

    @Column(length = 10000)
    @Basic(fetch = FetchType.LAZY)
    String observaciones;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 20 * 1024 * 1024)
    Byte[] contenido;

    public StreamedContent getStreamedContent() {

		String type = "";

		if (getFileName().toLowerCase().trim().endsWith(".pdf")) {
			type = "application/pdf";
		} else if (getFileName().toLowerCase().trim().endsWith(".xls")
				|| getFileName().toLowerCase().trim().endsWith(".xlsx")) {
			type = "application/vnd.ms-excel";
		} else if (getFileName().toLowerCase().trim().endsWith(".png")) {
			type = "image/png";
		} else if (getFileName().toLowerCase().trim().endsWith(".jpg")
				|| getFileName().toLowerCase().trim().endsWith(".jpeg")) {
			type = "image/jpeg";
		} else if (getFileName().toLowerCase().trim().endsWith(".doc")
				|| getFileName().toLowerCase().trim().endsWith(".docx")) {
			type = "application/msword";
		}

		return DefaultStreamedContent.builder()
				.stream(() -> new ByteArrayInputStream(ArrayUtils.toPrimitive(getContenido())))
				.contentType(type).build();
	}
}
