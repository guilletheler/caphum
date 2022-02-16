package com.gt.caphum.web.model.rrhh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gt.caphum.web.model.localizacion.Localidad;
import com.gt.caphum.web.model.usuarios.Usuario;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithObservaciones;

import org.hibernate.annotations.Formula;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Data
@Entity
@Table(name = "requerimientos_de_personal")
public class RequerimientoDePersonal implements IWithIntegerId, IWithObservaciones, Documentable {
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
    
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    
    @Enumerated(EnumType.STRING)
    EstadoRequerimientoDePersonal estado;
    
    @ManyToOne
    Usuario interesado;
    
    @ManyToOne
    Localidad localidad;

    @Enumerated(EnumType.STRING)
    CausaRequerimiento causa;

    String otraCausa;
    
    @Enumerated(EnumType.STRING)
    RelacionLaboral relacionLaboral;

    Double remuneracionPrevista;

    String beneficios;

    @Column(length = 5000)
    String horarios;

    @Enumerated(EnumType.STRING)
    Genero genero;

    Integer edadMinima;

    Integer edadMaxima;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "estudios_formales_requerimientos", joinColumns = @JoinColumn(name = "requerimiento_id"))
    @Column(name = "estudio")
    private List<String> estudiosFormales;

    @Formula("""
    (select GROUP_CONCAT(ESTUDIOS_FORMALES_REQUERIMIENTOS.estudio) 
    from ESTUDIOS_FORMALES_REQUERIMIENTOS 
    where ESTUDIOS_FORMALES_REQUERIMIENTOS.requerimiento_id = id)
    """)
    private String estudiosFormalesStr;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "estudios_no_formales_requerimientos", joinColumns = @JoinColumn(name = "requerimiento_id"))
    @Column(name = "estudio")
    private List<String> estudiosNoFormales;

    @Formula("""
    (select GROUP_CONCAT(ESTUDIOS_NO_FORMALES_REQUERIMIENTOS.estudio) 
    from ESTUDIOS_NO_FORMALES_REQUERIMIENTOS 
    where ESTUDIOS_NO_FORMALES_REQUERIMIENTOS.requerimiento_id = id)
    """)
    private String estudiosNoFormalesStr;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "idiomas_requerimientos", joinColumns = @JoinColumn(name = "requerimiento_id"))
    @Column(name = "idioma")
    private List<String> idiomas;

    @Formula("(select GROUP_CONCAT(IDIOMAS_REQUERIMIENTOS.idioma) from IDIOMAS_REQUERIMIENTOS where IDIOMAS_REQUERIMIENTOS.requerimiento_id = id)")
    private String idiomasStr;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "requerimientoDePersonal", cascade = CascadeType.ALL, orphanRemoval = true)
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
