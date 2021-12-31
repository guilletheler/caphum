package com.gt.caphum.web.model.rrhh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gt.caphum.web.model.CodigoNombre;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithObservaciones;

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
        implements Serializable, IWithIntegerId, IWithObservaciones {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public List<Documento> getDocumentos() {
        if(documentos == null) {
            documentos = new ArrayList<>();
        }
        return documentos;
    }

    public List<String> getAptitudes() {
        if(aptitudes == null) {
            aptitudes = new ArrayList<>();
        }
        return aptitudes;
    }
}