package com.gt.caphum.web.model.rrhh;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javax.persistence.Transient;

import com.gt.caphum.web.model.CodigoNombre;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithObservaciones;

import org.apache.commons.lang3.ArrayUtils;
import org.primefaces.model.file.UploadedFile;

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

  String contentType;

  @Temporal(TemporalType.DATE)
  Date fechaCreacion;

  @Temporal(TemporalType.DATE)
  private Date ultimaModificacion;

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

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @Transient
  UploadedFile uploadedFile;

  public void setUploadedFile(UploadedFile file) {
    Logger.getLogger(getClass().getName()).log(Level.INFO,
        "setUploadedFile: " + file.getFileName() + " " + file.getSize());
    this.uploadedFile = file;
    setContenido(ArrayUtils.toObject(file.getContent()));
    setFileName(file.getFileName());
    setContentType(file.getContentType());
  }

  public UploadedFile getUploadedFile() {
    return uploadedFile;
  }
}
