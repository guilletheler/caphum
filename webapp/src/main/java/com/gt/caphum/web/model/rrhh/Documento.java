package com.gt.caphum.web.model.rrhh;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "personas")
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
    Byte[] contenido;


}
