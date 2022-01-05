package com.gt.caphum.web.controller.dialog;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.gt.caphum.web.model.rrhh.Documento;
import com.gt.caphum.web.repo.rrhh.DocumentoRepo;

import org.apache.commons.lang3.ArrayUtils;
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
    private String fileName;

    public void preview(Documento documento) {

        Documento doc = documentoRepo.findById(documento.getId()).get();
        fileName = doc.getFileName();
        if (doc.getContenido() == null || doc.getContenido().length == 0) {
            return;
        }

        String type = "";

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

        streamedContent = DefaultStreamedContent.builder()
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

        // Logger.getLogger(getClass().getName()).log(Level.INFO, "Abriendo diálogo para
        // elegir artículo");

        PrimeFaces.current().dialog().openDynamic("/pages/dialogs/DocumentPreviewDialog.xhtml", options, null);
    }

    public boolean isRenderPdf() {
        return fileName.toLowerCase().endsWith(".pdf");
    }

    public boolean isRenderImage() {
        return fileName.toLowerCase().endsWith(".png") || fileName.toLowerCase().endsWith(".jpg")
                || fileName.toLowerCase().endsWith(".jpeg");
    }
}
