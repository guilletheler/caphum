/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\infra\converter\rrhh\PersonaConverter.java
 */
package com.gt.caphum.web.infra.converter.rrhh;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.gt.caphum.web.bean.EntityManagerHelperMB;
import com.gt.caphum.web.model.rrhh.Persona;

@FacesConverter(forClass = Persona.class)
public class PersonaConverter  implements Converter<Persona> {

	@Override
	public Persona getAsObject(FacesContext context, UIComponent component, String value) {
		EntityManagerHelperMB rhb= context.getApplication().evaluateExpressionGet(context, "#{entityManagerHelperMB}", EntityManagerHelperMB.class);
	    EntityManager em = rhb.getEm();
	    Persona tmp = em.find(Persona.class, Integer.valueOf(value));
	    return tmp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Persona value) {
		return value.getId().toString();
	}

	
}

