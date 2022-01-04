package com.gt.caphum.web.dto;

import java.io.Serializable;

import com.gt.caphum.web.model.CodigoNombre;
import com.gt.caphum.web.model.localizacion.Localidad;
import com.gt.caphum.web.model.localizacion.Pais;
import com.gt.caphum.web.model.localizacion.Provincia;

import lombok.Data;

@Data
public class LocalizacionDto implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * 0 Pais
     * 1 Provincia
     * 2 Localidad
     */
    Integer tipo;

    Pais pais;
    Provincia provincia;
    Localidad localidad;

    public LocalizacionDto(Pais pais) {
        this.tipo = 0;
        this.pais = pais;
    }

    public LocalizacionDto(Provincia provincia) {
        this.tipo = 1;
        this.provincia = provincia;
    }

    public LocalizacionDto(Localidad localidad) {
        this.tipo = 2;
        this.localidad = localidad;
    }

    public CodigoNombre getData() {
        switch (tipo) {
            case 0:
                return pais;
            case 1:
                return provincia;
            case 2:
                return localidad;
            default:
                return null;
        }
    }

    public Integer getCodigo() {
        return getData().getCodigo();
    }
    
    public void setCodigo(Integer codigo) {
        getData().setCodigo(codigo);
    }

    public String getNombre() {
        return getData().getNombre();
    }

    public void setNombre(String nombre) {
        getData().setNombre(nombre);
    }

    public boolean isLocalidad() {
        return tipo == 2;
    }

    public boolean isNotLocalidad() {
        return tipo != 2;
    }

    public String getCodigoPostal() {
        if(localidad != null) {
            return localidad.getCodigoPostal();
        }
        return null;
    }

    public void setCodigoPostal(String codigoPostal) {
        if(localidad != null) {
            localidad.setCodigoPostal(codigoPostal);
        }
    
    }

    public String getPrefijo() {
        if(localidad != null) {
            return localidad.getPrefijoTelefonico();
        }
        return null;
    }

    public void setPrefijo(String prefijo) {
        if(localidad != null) {
            localidad.setPrefijoTelefonico(prefijo);
        }
    }
}
