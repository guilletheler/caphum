package com.gt.caphum.web.model.rrhh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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

import com.gt.caphum.web.model.CodigoNombre;
import com.gt.caphum.web.model.localizacion.Localidad;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithObservaciones;

import org.hibernate.annotations.Formula;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "personas")
public class Persona extends CodigoNombre
        implements Documentable, Serializable, IWithIntegerId, IWithObservaciones {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String tipoDocumento;

    String nroDocumento;

    private String domicilio;

    @ManyToOne
    private Localidad localidad;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Temporal(TemporalType.DATE)
    private Date ultimaModificacion;

    @Column(length = 10000)
    @Basic(fetch = FetchType.LAZY)
    String observaciones;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @OneToMany
    private List<Documento> documentos;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "aptitudes_personas", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "aptitud")
    private List<String> aptitudes;

    @Formula("(select GROUP_CONCAT(APTITUDES_PERSONAS.aptitud) from APTITUDES_PERSONAS where APTITUDES_PERSONAS.persona_id = id)")
    private String aptitudesStr;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "estudios_formales_personas", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "estudio")
    private List<String> estudiosFormales;

    @Formula("(select GROUP_CONCAT(ESTUDIOS_FORMALES_PERSONAS.estudio) from ESTUDIOS_FORMALES_PERSONAS where ESTUDIOS_FORMALES_PERSONAS.persona_id = id)")
    private String estudiosFormalesStr;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "estudios_no_formales_personas", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "estudio")
    private List<String> estudiosNoFormales;

    @Formula("(select GROUP_CONCAT(ESTUDIOS_NO_FORMALES_PERSONAS.estudio) from ESTUDIOS_NO_FORMALES_PERSONAS where ESTUDIOS_NO_FORMALES_PERSONAS.persona_id = id)")
    private String estudiosNoFormalesStr;

    @Enumerated(EnumType.STRING)
    Genero genero;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "idiomas_personas", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "idioma")
    private List<String> idiomas;

    @Formula("(select GROUP_CONCAT(IDIOMAS_PERSONAS.idioma) from IDIOMAS_PERSONAS where IDIOMAS_PERSONAS.persona_id = id)")
    private String idiomasStr;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "telefonos_personas", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "telefono")
    private List<String> telefonos;

    @Formula("(select GROUP_CONCAT(TELEFONOS_PERSONAS.telefono) from TELEFONOS_PERSONAS where TELEFONOS_PERSONAS.persona_id = id)")
    private String telefonosStr;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "email_personas", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "emails")
    private List<String> emails;
    
    @ManyToOne
    private Puesto puestoActual;            

    @Formula("(select GROUP_CONCAT(EMAIL_PERSONAS.emails) from EMAIL_PERSONAS where EMAIL_PERSONAS.persona_id = id)")
    private String emailsStr;

    @Formula("DATEDIFF('day', fecha_Nacimiento, CURRENT_DATE) / 365.25")
    Integer edad;

    public List<Documento> getDocumentos() {
        if (documentos == null) {
            documentos = new ArrayList<>();
        }
        return documentos;
    }

    public List<String> getTelefonos() {
        if (telefonos == null) {
            telefonos = new ArrayList<>();
        }
        return telefonos;
    }

    public List<String> getEmails() {
        if (emails == null) {
            emails = new ArrayList<>();
        }
        return emails;
    }

    public List<String> getEstudiosFormales() {
        if (estudiosFormales == null) {
            estudiosFormales = new ArrayList<>();
        }
        return estudiosFormales;
    }

    public List<String> getEstudiosNoFormales() {
        if (estudiosNoFormales == null) {
            estudiosNoFormales = new ArrayList<>();
        }
        return estudiosNoFormales;
    }

    public List<String> getIdiomas() {
        if (idiomas == null) {
            idiomas = new ArrayList<>();
        }
        return idiomas;
    }

    public List<String> getAptitudes() {
        if (aptitudes == null) {
            aptitudes = new ArrayList<>();
        }
        return aptitudes;
    }
}