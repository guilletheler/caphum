package com.gt.caphum.web.model.rrhh;

import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "puestos")
public class Puesto extends CodigoNombre implements IWithIntegerId, IWithObservaciones, Serializable {
    public static final long serialVersionUID = 1L;

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
    @ElementCollection
	@CollectionTable(name = "aptitudes_requeridas_puesto", joinColumns = @JoinColumn(name = "puesto_id"))
	@Column(name = "aptitud")
	private List<String> aptitudesRequeridas;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
	@CollectionTable(name = "aptitudes_deseadas_puesto", joinColumns = @JoinColumn(name = "puesto_id"))
	@Column(name = "aptitud")
	private List<String> aptitudesDeseadas;
}
