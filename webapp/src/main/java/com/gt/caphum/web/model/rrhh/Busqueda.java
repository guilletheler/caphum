package com.gt.caphum.web.model.rrhh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gt.caphum.web.model.localizacion.Localidad;
import com.gt.caphum.web.model.usuarios.Usuario;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithObservaciones;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Data
@Entity
@Table(name = "busquedas")
public class Busqueda implements IWithIntegerId, IWithObservaciones {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cantidadPuestos;

    @ManyToOne
    private Puesto puesto;
    
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    
    @Temporal(TemporalType.DATE)
    private Date inicio;
    
    @Temporal(TemporalType.DATE)
    private Date finalizacion;
    
    @Enumerated(EnumType.STRING)
    EstadoBusqueda estado;
    
    @ManyToOne
    Usuario interesado;
    
    @ManyToOne
    Localidad localidad;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "busqueda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluacion> evaluaciones;

    @Column(length = 10000)
    @Basic(fetch = FetchType.LAZY)
    String observaciones;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @OneToMany
    private List<Documento> documentos;

    public List<Evaluacion> getEvaluaciones() {
        if (evaluaciones == null) {
            evaluaciones = new ArrayList<>();
        }
        return evaluaciones;
    }

    public List<Documento> getDocumentos() {
        if (documentos == null) {
            documentos = new ArrayList<>();
        }
        return documentos;
    }
}
