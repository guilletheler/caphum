/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\infra\converter\rrhh\RequerimientoDePersonalConverter.java
 */
package com.gt.caphum.web.infra.converter.rrhh;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.gt.caphum.web.bean.EntityManagerHelperMB;
import com.gt.caphum.web.model.rrhh.RequerimientoDePersonal;

@FacesConverter(forClass = RequerimientoDePersonal.class)
public class RequerimientoDePersonalConverter  implements Converter<RequerimientoDePersonal> {

	@Override
	public RequerimientoDePersonal getAsObject(FacesContext context, UIComponent component, String value) {
		EntityManagerHelperMB rhb= context.getApplication().evaluateExpressionGet(context, "#{entityManagerHelperMB}", EntityManagerHelperMB.class);
	    EntityManager em = rhb.getEm();
	    RequerimientoDePersonal tmp = em.find(RequerimientoDePersonal.class, Integer.valueOf(value));
	    return tmp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, RequerimientoDePersonal value) {
		return value.getId().toString();
	}

	
}

