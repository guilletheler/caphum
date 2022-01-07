package com.gt.caphum.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gt.caphum.web.model.rrhh.Documentable;
import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.CollectionLazyDataModel;

import org.primefaces.model.LazyDataModel;

import lombok.Data;

@Data
public class DocumentableHelper {

    Documentable documentable;

    List<Documento> documentosEliminados;

    LazyDataModel<Documento> documentos;

    private Integer nextCodigo;

    public DocumentableHelper(Documentable documentable, Integer nextCodigo) {
        this.documentable = documentable;
        this.nextCodigo = nextCodigo;
        documentos = new CollectionLazyDataModel<Documento>(Documento.class, documentable.getDocumentos() , "id");
        this.documentosEliminados = new ArrayList<>();
    }

    public void crearDocumento() {
        Documento documento = new Documento();
        documento.setNombre("Nuevo Documento");
        documento.setCodigo(nextCodigo++);
        documento.setFechaCreacion(new Date());

        documentable.getDocumentos().add(documento);
        ((CollectionLazyDataModel<Documento>) documentos).updateSize();

        Logger.getLogger(getClass().getName()).log(Level.INFO, "Documento creado " + documentos.getRowCount());
    }

    public void borrarDocumento(Documento documento) {

		documentable.getDocumentos().remove(documento);
		((CollectionLazyDataModel<Documento>) documentos).updateSize();
		documentosEliminados.add(documento);
	}
}
