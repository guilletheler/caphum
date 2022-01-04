package com.gt.caphum.web.model.localizacion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gt.caphum.web.model.CodigoNombre;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "localidades")
public class Localidad extends CodigoNombre implements IWithIntegerId, Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Provincia provincia;

    String codigoPostal;

    String prefijoTelefonico;
}
