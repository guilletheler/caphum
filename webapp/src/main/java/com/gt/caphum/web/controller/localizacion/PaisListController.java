/**
 * Generado con CRUDO
 * D:\prog\java_mvn\caphum\webapp\src\main\java\com\gt\caphum\web\controller\localizacion\PaisListController.java
 */
package com.gt.caphum.web.controller.localizacion;

import static com.gt.toolbox.spb.webapps.commons.infra.utils.Utils.addDetailMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.gt.caphum.web.dto.LocalizacionDto;
import com.gt.caphum.web.model.localizacion.Localidad;
import com.gt.caphum.web.model.localizacion.Pais;
import com.gt.caphum.web.model.localizacion.Provincia;
import com.gt.caphum.web.service.localizacion.PaisService;

import org.omnifaces.util.Faces;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;

import lombok.Getter;

@Named
@ViewScoped
public class PaisListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	PaisService paisService;

	@Getter
	TreeNode rootNode;

	List<LocalizacionDto> borrados;

	@PostConstruct
	private void init() {
		if (Faces.isAjaxRequest()) {
			return;
		}

		// lazyDataModel = new EntityLazyDataModel<>(paisService, Pais.class, "id");

		rootNode = new DefaultTreeNode("root", null);

		for (Pais pais : paisService.getRepo().findAll()) {
			TreeNode paisNode = new DefaultTreeNode(new LocalizacionDto(pais), rootNode);

			for (Provincia provincia : pais.getProvincias()) {
				TreeNode provinciaNode = new DefaultTreeNode(new LocalizacionDto(provincia), paisNode);

				for (Localidad localidad : provincia.getLocalidades()) {
					new DefaultTreeNode(new LocalizacionDto(localidad), provinciaNode);
				}
			}
		}

		borrados = new ArrayList<>();
	}

	public void borrarPais(Pais pais) {
		paisService.getRepo().delete(pais);
		addDetailMessage("Pais " + pais.getNombre() + " borrado exitosamente");
	}

	public SortMeta getDefaultSortBy() {
		return SortMeta.builder().field("id").order(SortOrder.ASCENDING).build();
	}

	public boolean isLocalidadNode(LocalizacionDto node) {
		return node.getTipo() == 2;
	}

	public void addPais() {
		Pais pais = new Pais();
		pais.setNombre("Nuevo Pais");
		pais.setCodigo(paisService.getRepo().nextCodigo());
		new DefaultTreeNode(new LocalizacionDto(pais), rootNode);
	}

	public void agregar(LocalizacionDto loc) {
		
		TreeNode parent = findNode(loc);

		if(loc.getTipo() == 0) {
			Provincia provincia = new Provincia();
			provincia.setNombre("Nueva Provincia");
			provincia.setCodigo(1);
			provincia.setPais(((LocalizacionDto) parent.getData()).getPais());
			((LocalizacionDto) parent.getData()).getPais().getProvincias().add(provincia);
			new DefaultTreeNode(new LocalizacionDto(provincia), parent);
		} else if(loc.getTipo() == 1) {
			Localidad localidad = new Localidad();
			localidad.setNombre("Nueva Localidad");
			localidad.setCodigo(1);
			localidad.setProvincia(((LocalizacionDto) parent.getData()).getProvincia());
			((LocalizacionDto) parent.getData()).getProvincia().getLocalidades().add(localidad);
			new DefaultTreeNode(new LocalizacionDto(localidad), parent);
		}
		parent.setExpanded(true);
	}

	public void borrar(LocalizacionDto loc) {

		borrados.add(loc);

		TreeNode node = findNode(loc);

		TreeNode parent = node.getParent();

		parent.getChildren().remove(node);
	}

	TreeNode findNode(LocalizacionDto dto) {
		return findNode(dto, rootNode);
	}

	TreeNode findNode(LocalizacionDto dto, TreeNode parent) {

		TreeNode ret = null;

		for (TreeNode node : parent.getChildren()) {
			if (node.getData().equals(dto)) {
				ret = node;
			} else {
				ret = findNode(dto, node);
			}

			if (ret != null) {
				break;
			}
		}

		return ret;
	}

	public void save() {

		for(LocalizacionDto borrado : borrados) {
			if(borrado.getTipo() == 0) {
				// borr un pais completo
				paisService.getRepo().delete(borrado.getPais());
			} else if(borrado.getTipo() == 1) {
				// borr una provincia
				Pais pais = borrado.getProvincia().getPais();
				pais.getProvincias().remove(borrado.getProvincia());
			} else if(borrado.getTipo() == 2) {
				// borr una localidad
				Provincia provincia = borrado.getLocalidad().getProvincia();
				provincia.getLocalidades().remove(borrado.getLocalidad());
			}
		}

		for (TreeNode node : rootNode.getChildren()) {
			LocalizacionDto dto = (LocalizacionDto) node.getData();

			Pais pais = dto.getPais();

			for(Provincia prov : pais.getProvincias()) {
				for(Localidad localidad : prov.getLocalidades()) {
					Logger.getLogger(getClass().getName()).log(Level.INFO, "Grabando localidad " + localidad.toString());
				}
			}

			paisService.getRepo().save(pais);

		}
		
		addDetailMessage("Paises guardados");

	}
}
