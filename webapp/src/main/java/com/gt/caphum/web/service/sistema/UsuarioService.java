package com.gt.caphum.web.service.sistema;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.gt.caphum.web.model.usuarios.UserRol;
import com.gt.caphum.web.model.usuarios.Usuario;
import com.gt.caphum.web.repo.sistema.UsuarioRepo;
import com.gt.toolbox.spb.webapps.commons.infra.service.QueryHelper;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.LazyDMFiller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;

@Service
@Transactional
public class UsuarioService implements LazyDMFiller<Usuario> {

	@Getter
	@Autowired
	UsuarioRepo repo;

	public Page<Usuario> findByFilter(Map<String, String> filters, Pageable pageable) {
		return repo.findAll(QueryHelper.getFilterSpecification(filters), pageable);
	}

	public Usuario getCurrentUser() {
		if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null
				|| SecurityContextHolder.getContext().getAuthentication().getDetails() == null) {
			return null;
		}
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
	}

	public Usuario save(Usuario usuario) {
		return repo.save(usuario);
	}

	public String findOrCreateParam(String paramName, String defaultValue) {
		return findOrCreateParam(null, paramName, defaultValue);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String findOrCreateParam(Usuario usuario, String paramName, String defaultValue) {

		if (usuario == null) {
			usuario = getCurrentUser();
		}

		Integer idUsuario = usuario.getId();

		usuario = getRepo().findById(idUsuario).orElse(null);

		if (usuario == null) {
			throw new RuntimeException("No se puede encontrar usuario con id " + idUsuario);
		}

		if (!usuario.getParametros().containsKey(paramName)) {
			usuario.getParametros().put(paramName, defaultValue);
			usuario = getRepo().save(usuario);
		}

		return usuario.getParametros().get(paramName);
	}

	public Usuario saveParam(String paramName, String defaultValue) {
		return saveParam(null, paramName, defaultValue);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Usuario saveParam(Usuario usuario, String paramName, String defaultValue) {
		if (usuario == null) {
			usuario = getCurrentUser();
		}

		Integer idUsuario = usuario.getId();

		usuario = getRepo().findById(idUsuario).orElse(null);

		if (usuario == null) {
			throw new RuntimeException("No se puede encontrar usuario con id " + idUsuario);
		}

		usuario.getParametros().put(paramName, defaultValue);

		return getRepo().save(usuario);
	}
	
	public boolean inRole(String roles) {

		Usuario currentUser = getCurrentUser();

		if (currentUser == null) {
			return false;
		}

		List<String> anyRolList = Arrays.asList(roles.split(","));

		Set<UserRol> userEffectiveRoleList = getUserEffectiveRoles(currentUser);

		boolean ret = userEffectiveRoleList.stream().anyMatch(
				userRol -> anyRolList.stream().anyMatch(anyRol -> userRol.name().equalsIgnoreCase(anyRol.trim())));

		// Logger.getLogger(getClass().getName()).log(Level.INFO, "test {0} in effective: {1} ? {2}",
		// 		new Object[] { roles, Arrays.toString(userEffectiveRoleList.toArray(UserRol[]::new)), ret });

		return ret;
	}

	public Set<UserRol> getUserEffectiveRoles(Usuario currentUser) {
		Set<UserRol> userEffectiveRoleList = currentUser.getRoles().stream()
				.flatMap(rol -> UserRol.getContainedRoles(rol).stream()).collect(Collectors.toSet());
		return userEffectiveRoleList;
	}

	public List<Usuario> findUsuariosInteresados() {
		return repo.findByRoles(UserRol.INTERESADO);
	}
}
