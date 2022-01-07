package com.gt.caphum.web.controller.dialog;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;

import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.repo.rrhh.DocumentoRepo;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.LazyInitializationException;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

@Named
public class DocumentPreviewDialogController {

    @Autowired
    private DocumentoRepo documentoRepo;

    @Getter
    private StreamedContent streamedContent;

    @Getter
    private Documento doc;

    public void preview(Documento documento) {

        doc = documento;

        try {
            if (doc.getContenido() == null || doc.getContenido().length == 0) {
                return;
            }
        } catch (LazyInitializationException ex) {
            doc = documentoRepo.findById(documento.getId()).get();
            if (doc.getContenido() == null || doc.getContenido().length == 0) {
                return;
            }
        }

        String type = doc.getContentType();

        if (type == null || type.isEmpty()) {
            if (doc.getFileName().toLowerCase().trim().endsWith(".pdf")) {
                type = "application/pdf";
            } else if (doc.getFileName().toLowerCase().trim().endsWith(".xls")
                    || doc.getFileName().toLowerCase().trim().endsWith(".xlsx")) {
                type = "application/vnd.ms-excel";
            } else if (doc.getFileName().toLowerCase().trim().endsWith(".png")) {
                type = "image/png";
            } else if (doc.getFileName().toLowerCase().trim().endsWith(".jpg")
                    || doc.getFileName().toLowerCase().trim().endsWith(".jpeg")) {
                type = "image/jpeg";
            } else if (doc.getFileName().toLowerCase().trim().endsWith(".doc")
                    || doc.getFileName().toLowerCase().trim().endsWith(".docx")) {
                type = "application/msword";
            }
        }

        streamedContent = DefaultStreamedContent.builder()
                .name(doc.getFileName())
                .stream(() -> new ByteArrayInputStream(ArrayUtils.toPrimitive(doc.getContenido())))
                .contentType(type).build();

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);

        options.put("width", "90%");
        options.put("height", "90%");

        options.put("contentWidth", "100%");
        options.put("contentHeight", "90%");
        options.put("headerElement", "customheader");

        Logger.getLogger(getClass().getName()).log(Level.INFO,
                "Cargando preview de documento: " + doc.getFileName() + " " + doc.getContenido().length);

        PrimeFaces.current().dialog().openDynamic("/pages/dialogs/DocumentPreviewDialog.xhtml", options, null);

    }

    public boolean isRenderPdf() {
        return doc.getFileName().toLowerCase().endsWith(".pdf");
    }

    public boolean isRenderImage() {
        return doc.getFileName().toLowerCase().endsWith(".png") || doc.getFileName().toLowerCase().endsWith(".jpg")
                || doc.getFileName().toLowerCase().endsWith(".jpeg");
    }

}
