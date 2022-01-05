/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gt.caphum.web.model.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.Getter;

/**
 *
 * @author guille
 */
public enum UserRol {
    SYSADMIN("ADMINISTRADOR DE SISTEMAS", "ABM usuarios, ABM parámetros de sistema", "", "/pages/sistema/**"),
    RRHH("R.R.H.H.", "", "SYSADMIN", "/pages/rrhh/**"),
    INTERESADO("INTERESADO", "", "SYSADMIN", "/pages/interesado/**"),
    USUARIO("CAMBIO DE CONTRASEÑA", "", "SYSADMIN", "/pages/usuario/**"),
    WEBSERVICES("WEBSERVICES", "", "SYSADMIN", "/ws/**"),
    AYUDA("AYUDA", "Muestra la ayuda del sistema", "SYSADMIN", "/pages/ayuda/**");

    @Getter
    String nombre;

    @Getter
    String descripcion;

    @Getter
    String padres;

    @Getter
    String[] folders;

    private UserRol(String nombre, String descripcion, String padres, String... folders) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.padres = padres;
        this.folders = folders;
    }

    public static UserRol parse(String nombre) {
        for (UserRol userRol : UserRol.values()) {
            if (Objects.equals(nombre, userRol.name())) {
                return userRol;
            }
        }
        return null;
    }

    public static Set<UserRol> parseList(String roles) {

        Set<UserRol> ret = new HashSet<>();

        if (roles != null && !roles.isEmpty()) {
            for (String str : roles.split(",")) {
                UserRol userRol = parse(str.trim().toUpperCase());
                if (userRol != null) {
                    ret.add(userRol);
                } else {
                    Logger.getLogger(UserRol.class.getName()).log(Level.WARNING, "No se encontró el rol {0}", str);
                }
            }
        }
        return ret;
    }

    /**
     * Lista de usuarios con persmisos para las diferentes páginas
     * 
     * @return
     */
    public static Map<String, Set<String>> getPermissionsMap() {
        Map<String, Set<String>> ret = new HashMap<>();

        for (UserRol userRol : UserRol.values()) {
            for (String folder : userRol.getFolders()) {

                if (folder == null || folder.isEmpty()) {
                    continue;
                }

                if (!ret.containsKey(folder)) {
                    ret.put(folder, new HashSet<>());
                }

                getEffectiveRoles(userRol).forEach(r -> ret.get(folder).add(r.name()));

            }
        }

        return ret;
    }

    public static Set<UserRol> getEffectiveRoles(UserRol userRol) {
        Set<UserRol> ret = new HashSet<>();
        ret.add(userRol);

        Set<UserRol> rolesPadre = parseList(userRol.getPadres());

        if (userRol.getPadres() != null && userRol.getPadres().length() > 0 && rolesPadre.isEmpty()) {
            Logger.getLogger(UserRol.class.getName()).log(Level.WARNING,
                    "Roles padres no vacios pero no encontrado {0}", userRol.getPadres());
        }

        ret.addAll(rolesPadre);

        return ret;
    }

    public static Set<UserRol> getContainedRoles(UserRol userRol) {
        Set<UserRol> ret = new HashSet<>();
        ret.add(userRol);

        for(UserRol ur : UserRol.values()) {
            Set<UserRol> rolesPadre = parseList(ur.getPadres());
            if(rolesPadre.contains(userRol)) {
                ret.addAll(getContainedRoles(ur));
            }
        }

        return ret;
    }
}
