package com.gt.caphum.web.controller.sistema;

import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.gt.caphum.web.model.usuarios.UserRol;
import com.gt.caphum.web.model.usuarios.Usuario;
import com.gt.caphum.web.service.sistema.UsuarioService;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.EntityLazyDataModel;

import org.primefaces.model.LazyDataModel;

import lombok.Getter;

@Named
@ViewScoped
public class UsuarioListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioService usuarioService;

	@Getter
	LazyDataModel<Usuario> lazyDataModel;

	@PostConstruct
	private void init() {
		lazyDataModel = new EntityLazyDataModel<>(usuarioService);
	}

	public void borrarUsuario(Usuario usuario) {
		usuarioService.getRepo().delete(usuario);
		addDetailMessage("Usuario " + usuario.getNombre() + " borrado exitosamente");
	}

	public String getRoles(Usuario usuario) {
		return usuarioService.getUserEffectiveRoles(usuario).stream().map(UserRol::name).sorted()
				.collect(Collectors.joining(", "));
	}
}
