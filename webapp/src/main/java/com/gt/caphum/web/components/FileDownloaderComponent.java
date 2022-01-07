package com.gt.caphum.web.components;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.service.rrhh.DocumentoService;

import org.apache.commons.lang3.ArrayUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@RequestScoped
public class FileDownloaderComponent {

    @Autowired
    DocumentoService documentoService;

    public StreamedContent downloadContent(Documento documento) {

        if (documento == null) {
            return null;
        }

        Documento doc = documentoService.getRepo().findById(documento.getId()).get();

        Logger.getLogger(getClass().getName()).log(Level.INFO, "Downloading file: {0}", doc.getNombre() + "."
                + doc.getFileName() + " " + doc.getContentType() + " " + doc.getContenido().length);

        return DefaultStreamedContent.builder()
                .name(doc.getFileName())
                .contentType(doc.getContentType())
                .stream(() -> new ByteArrayInputStream(ArrayUtils.toPrimitive(doc.getContenido())))
                .build();
    }
}
