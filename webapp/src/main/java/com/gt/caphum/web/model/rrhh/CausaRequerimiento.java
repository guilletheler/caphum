package com.gt.caphum.web.model.rrhh;

import lombok.Getter;

public enum CausaRequerimiento {
    
    NUEVO_PUESTO("Nuevo puesto"),
    MAYOR_CANTIDAD("Mayor cantidad"),
    MODIFICACION_CONTENIDO_PUESTO("Modificación del contenido del puesto"),
    AUSENCIA_TEMPORARIA("Ausencia temporRIA"),
    DESPIDO("Despido"),
    REEMPLAZO_LICENCIAS_ESPECIALES("Reemplazo POR licencias especiales u otras razones"),
    RENUNCIA("Renuncia"),
    REESTRUCTURACION("Reestructuración del sector"),
    OTRA("Otra");

    @Getter
    String descripcion;

    private CausaRequerimiento(String descripcion) {
        this.descripcion = descripcion;
    }
}
