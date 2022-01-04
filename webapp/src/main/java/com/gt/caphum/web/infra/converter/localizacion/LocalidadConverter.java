/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\infra\converter\localizacion\LocalidadConverter.java
 */
package com.gt.caphum.web.infra.converter.localizacion;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.gt.caphum.web.bean.EntityManagerHelperMB;
import com.gt.caphum.web.model.localizacion.Localidad;

@FacesConverter(forClass = Localidad.class)
public class LocalidadConverter  implements Converter<Localidad> {

	@Override
	public Localidad getAsObject(FacesContext context, UIComponent component, String value) {
		EntityManagerHelperMB rhb= context.getApplication().evaluateExpressionGet(context, "#{entityManagerHelperMB}", EntityManagerHelperMB.class);
	    EntityManager em = rhb.getEm();
	    Localidad tmp = em.find(Localidad.class, Integer.valueOf(value));
	    return tmp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Localidad value) {
		return value.getId().toString();
	}

	
}

