package com.gt.caphum.web.bean;

import javax.ejb.Singleton;
import javax.inject.Named;

import com.gt.caphum.web.model.rrhh.EstadoBusqueda;
import com.gt.caphum.web.model.rrhh.EstadoEvaluacion;
import com.gt.caphum.web.model.usuarios.UserRol;

/**
 * Clase que devuelve los valores de los enums
 * 
 * @author PortalTheler
 *
 */
@Named
@Singleton
public class EnumsMB {

	public UserRol[] getRoles() {
		return UserRol.values();
	}

	public EstadoBusqueda[] getEstadoBusqueda() {
		return EstadoBusqueda.values();
	}

	public EstadoEvaluacion[] getEstadoEvaluacion() {
		return EstadoEvaluacion.values();
	}
}
