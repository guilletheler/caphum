package com.gt.caphum.web.model.localizacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gt.caphum.web.model.CodigoNombre;
import com.gt.toolbox.spb.webapps.commons.infra.model.IWithIntegerId;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "paises")
public class Pais extends CodigoNombre implements IWithIntegerId, Serializable {
    
    public static final long serialVersionUID = 1L;
    
    
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Provincia> provincias;
    
    public List<Provincia> getProvincias() {
        if (provincias == null) {
            provincias = new ArrayList<>();
        }
        return provincias;
    }

}
