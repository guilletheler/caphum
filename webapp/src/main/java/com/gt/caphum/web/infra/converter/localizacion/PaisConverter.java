/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\infra\converter\localizacion\PaisConverter.java
 */
package com.gt.caphum.web.infra.converter.localizacion;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.gt.caphum.web.bean.EntityManagerHelperMB;
import com.gt.caphum.web.model.localizacion.Pais;

@FacesConverter(forClass = Pais.class)
public class PaisConverter  implements Converter<Pais> {

	@Override
	public Pais getAsObject(FacesContext context, UIComponent component, String value) {
		EntityManagerHelperMB rhb= context.getApplication().evaluateExpressionGet(context, "#{entityManagerHelperMB}", EntityManagerHelperMB.class);
	    EntityManager em = rhb.getEm();
	    Pais tmp = em.find(Pais.class, Integer.valueOf(value));
	    return tmp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Pais value) {
		return value.getId().toString();
	}

	
}

