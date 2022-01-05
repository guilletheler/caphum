package com.gt.caphum.web.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.gt.caphum.web.model.usuarios.UserRol;
import com.gt.caphum.web.model.usuarios.Usuario;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Getter;

@Named
@ViewScoped
public class SessionMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	private Usuario currentUser;

	@PostConstruct
	public void init() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null && context.getAuthentication() != null) {
			currentUser = (Usuario) context.getAuthentication().getDetails();
		}
	}

	public boolean inRole(String roles) {
		if (currentUser == null) {
			return false;
		}

		List<String> anyRolList = Arrays.asList(roles.split(","));

		List<UserRol> userEffectiveRoleList = currentUser.getRoles().stream()
				.flatMap(rol -> UserRol.getContainedRoles(rol).stream()).collect(Collectors.toList());

		boolean ret = userEffectiveRoleList.stream().anyMatch(
				userRol -> anyRolList.stream().anyMatch(anyRol -> userRol.name().equalsIgnoreCase(anyRol.trim())));

		// Logger.getLogger(SessionMB.class.getName()).log(Level.INFO, "test {0} in effective: {1} ? {2}",
		// 		new Object[] { roles, Arrays.toString(userEffectiveRoleList.toArray(UserRol[]::new)), ret });

		return ret;
	}
}
