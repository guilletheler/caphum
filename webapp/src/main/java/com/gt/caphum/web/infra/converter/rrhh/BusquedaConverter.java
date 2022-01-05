/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\infra\converter\rrhh\BusquedaConverter.java
 */
package com.gt.caphum.web.infra.converter.rrhh;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.gt.caphum.web.bean.EntityManagerHelperMB;
import com.gt.caphum.web.model.rrhh.Busqueda;

@FacesConverter(forClass = Busqueda.class)
public class BusquedaConverter  implements Converter<Busqueda> {

	@Override
	public Busqueda getAsObject(FacesContext context, UIComponent component, String value) {
		EntityManagerHelperMB rhb= context.getApplication().evaluateExpressionGet(context, "#{entityManagerHelperMB}", EntityManagerHelperMB.class);
	    EntityManager em = rhb.getEm();
	    Busqueda tmp = em.find(Busqueda.class, Integer.valueOf(value));
	    return tmp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Busqueda value) {
		return value.getId().toString();
	}

	
}

