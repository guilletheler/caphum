/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\infra\converter\localizacion\ProvinciaConverter.java
 */
package com.gt.caphum.web.infra.converter.localizacion;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.gt.caphum.web.bean.EntityManagerHelperMB;
import com.gt.caphum.web.model.localizacion.Provincia;

@FacesConverter(forClass = Provincia.class)
public class ProvinciaConverter  implements Converter<Provincia> {

	@Override
	public Provincia getAsObject(FacesContext context, UIComponent component, String value) {
		EntityManagerHelperMB rhb= context.getApplication().evaluateExpressionGet(context, "#{entityManagerHelperMB}", EntityManagerHelperMB.class);
	    EntityManager em = rhb.getEm();
	    Provincia tmp = em.find(Provincia.class, Integer.valueOf(value));
	    return tmp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Provincia value) {
		return value.getId().toString();
	}

	
}

