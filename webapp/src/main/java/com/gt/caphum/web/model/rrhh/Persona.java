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

    @Column(length = 10000)
    private String aptitudes;

    @Column(length = 10000)
    private String estudiosFormales;

    @Column(length = 10000)
    private String estudiosNoFormales;

    @Enumerated(EnumType.STRING)
    Genero genero;

    private String idiomas;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "telefonos_personas", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "telefono")
    private List<String> telefonos;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "email_personas", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "emails")
    private List<String> emails;

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
}