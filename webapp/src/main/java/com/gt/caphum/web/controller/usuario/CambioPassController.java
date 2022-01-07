package com.gt.caphum.web.controller.usuario;

import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.gt.caphum.web.model.usuarios.Usuario;
import com.gt.caphum.web.service.sistema.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CambioPassController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	UsuarioService usuarioService;

	@Getter
	@Setter
	Integer id;

	@Getter
	@Setter
	String curpasswd;

	@Getter
	@Setter
	String newpasswd1;

	@Getter
	@Setter
	String newpasswd2;

	public String save() {

		Usuario cur = usuarioService.getCurrentUser();

		if (!Objects.equals(newpasswd1, newpasswd2)) {
			addDetailMessage("La contraseña actual es incorrecta", FacesMessage.SEVERITY_ERROR);
		} else if (cur.equalPasswd(getCurpasswd())) {
			cur.setAndEncryptPassword(newpasswd1);
			usuarioService.getRepo().save(cur);
			addDetailMessage("Contraseña cambiada exitosamente");
			return "/index.xhtml?faces-redirect=true";
		} else {
			addDetailMessage("La contraseña actual es incorrecta", FacesMessage.SEVERITY_ERROR);
		}

		return null;
	}

	public void clear() {
		curpasswd = "";
		newpasswd1 = "";
		newpasswd2 = "";

		id = null;
	}

}
