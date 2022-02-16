package com.gt.caphum.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gt.caphum.web.infra.ICodeGenerator;
import com.gt.caphum.web.model.rrhh.Documentable;
import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.toolbox.spb.webapps.commons.infra.datamodel.CollectionLazyDataModel;

import org.primefaces.model.LazyDataModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentableHelper implements ICodeGenerator {

    Documentable documentable;

    @Getter(AccessLevel.NONE)
    List<Documento> documentosEliminados;

    @Getter(AccessLevel.NONE)
    LazyDataModel<Documento> documentos;

    private Integer nextCodigo;

    private ICodeGenerator codeGen;

    @Builder.Default
    private boolean readOnly = false;

    public Documento crearDocumento() {
        Documento documento = new Documento();
        documento.setNombre("Nuevo Documento");
        if (codeGen != null) {
            documento.setCodigo(codeGen.incrementarCodigo());
        } else {
            documento.setCodigo(incrementarCodigo());
        }

        documento.setFechaCreacion(new Date());

        documentable.getDocumentos().add(documento);
        ((CollectionLazyDataModel<Documento>) documentos).updateSize();

        Logger.getLogger(getClass().getName()).log(Level.INFO, "Documento creado " + documentos.getRowCount());

        return documento;
    }

    public void borrarDocumento(Documento documento) {

        documentable.getDocumentos().remove(documento);
        ((CollectionLazyDataModel<Documento>) documentos).updateSize();
        documentosEliminados.add(documento);
    }

    public Integer incrementarCodigo() {
        return nextCodigo++;
    }

    public LazyDataModel<Documento> getDocumentos() {
        if (documentos == null) {
            documentos = new CollectionLazyDataModel<Documento>(Documento.class, documentable.getDocumentos(), "id");
        }
        return this.documentos;
    }

    public List<Documento> getDocumentosEliminados() {
        if (documentosEliminados == null) {
            documentosEliminados = new ArrayList<>();
        }
        return this.documentosEliminados;
    }

}
