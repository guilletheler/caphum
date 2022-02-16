package com.gt.caphum.web.model.rrhh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithObservaciones;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Data
@Entity
@Table(name = "evaluaciones")
public class Evaluacion implements Documentable, IWithIntegerId, IWithObservaciones, Serializable {
    
    public static final long serialVersionUID = 1L;

    /**
     * id
     */
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Short puntaje;

    @ManyToOne
    @NotNull
    private RequerimientoDePersonal requerimientoDePersonal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoEvaluacion estado;

    @NotNull
    @ManyToOne
    private Persona persona;

    @Column(length = 10000)
    @Basic(fetch = FetchType.LAZY)
    String observaciones;

    @Temporal(TemporalType.DATE)
    private Date fechaEntrevista;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @OneToMany
    private List<Documento> documentos;

    public List<Documento> getDocumentos() {
        if (documentos == null) {
            documentos = new ArrayList<Documento>();
        }
        return documentos;
    }
}
