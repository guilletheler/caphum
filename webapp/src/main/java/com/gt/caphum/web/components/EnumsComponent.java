package com.gt.caphum.web.components;

import com.gt.caphum.web.model.rrhh.EstadoRequerimientoDePersonal;
import com.gt.caphum.web.model.rrhh.EstadoEvaluacion;
import com.gt.caphum.web.model.usuarios.UserRol;

import org.springframework.stereotype.Component;

/**
 * Clase que devuelve los valores de los enums
 * 
 * @author PortalTheler
 *
 */
@Component
public class EnumsComponent {

	public UserRol[] getRoles() {
		return UserRol.values();
	}

	public EstadoRequerimientoDePersonal[] getEstadoRequerimientoDePersonal() {
		return EstadoRequerimientoDePersonal.values();
	}

	public EstadoEvaluacion[] getEstadoEvaluacion() {
		return EstadoEvaluacion.values();
	}
}
