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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gt.caphum.web.model.CodigoNombre;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithObservaciones;

import org.hibernate.annotations.Formula;

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

    String sector;
    
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

    @Formula("(select GROUP_CONCAT(APTITUDES_REQUERIDAS_PUESTO.aptitud) from APTITUDES_REQUERIDAS_PUESTO where APTITUDES_REQUERIDAS_PUESTO.puesto_id = id)")
    private String aptitudesRequeridasStr;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "aptitudes_deseadas_puesto", joinColumns = @JoinColumn(name = "puesto_id"))
    @Column(name = "aptitud")
    private List<String> aptitudesDeseadas;

    @Formula("(select GROUP_CONCAT(APTITUDES_DESEADAS_PUESTO.aptitud) from APTITUDES_DESEADAS_PUESTO where APTITUDES_DESEADAS_PUESTO.puesto_id = id)")
    private String aptitudesDeseadasStr;

    @Column(length = 10000)
    String horarios;

    @Column(length = 10000)
    @Basic(fetch = FetchType.LAZY)
    String descripcion;

    @ManyToOne
    Puesto puestoSuperior;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "puestoSuperior")
    List<Puesto> puestosSubordinados = new ArrayList<>();

    public List<Puesto> getPuestosSubordinados() {
        if (puestosSubordinados == null) {
            puestosSubordinados = new ArrayList<>();
        }
        return puestosSubordinados;
    }

    public List<String> getAptitudesRequeridas() {
        if (aptitudesRequeridas == null) {
            aptitudesRequeridas = new ArrayList<>();
        }
        return aptitudesRequeridas;
    }

    public List<String> getAptitudesDeseadas() {
        if (aptitudesDeseadas == null) {
            aptitudesDeseadas = new ArrayList<>();
        }
        return aptitudesDeseadas;
    }
}
