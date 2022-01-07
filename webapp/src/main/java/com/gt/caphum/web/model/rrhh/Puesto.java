package com.gt.caphum.web.model.rrhh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

    @Column(length = 10000)
    private String aptitudesRequeridas;

    @Column(length = 10000)
    private String aptitudesDeseadas;

    @Column(length = 10000)
    String horarios;

    @ManyToOne
    Puesto puestoSuperior;

    @Column(length = 10000)
    @Basic(fetch = FetchType.LAZY)
    String descripcion;

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

}
