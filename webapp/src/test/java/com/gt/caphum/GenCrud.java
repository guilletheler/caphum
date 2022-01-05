package com.gt.caphum;


import java.io.File;

import com.gt.caphum.web.model.rrhh.Busqueda;
import com.gt.crudo.CrudCreator;
import com.gt.crudo.CrudoFileWriter;

import org.junit.jupiter.api.Test;

//import com.gt.lotinfo.web.model.plataforma.AbstractApuesta;

public class GenCrud {

	@Test
	public void generaCud() {
		CrudCreator crudCreator = new CrudCreator(Busqueda.class);
		crudCreator.setProjectSourceFolder(new File("."));
		crudCreator.generateListController(System.out);
		System.out.println("\n\n\n\n");
		crudCreator.generateEditController(System.out);
		System.out.println("\n\n\n\n");
		crudCreator.generateListPage(System.out);
		System.out.println("\n\n\n\n");
		crudCreator.generateEditPage(System.out);

		CrudoFileWriter.writeAll(crudCreator, "rrhh", false);
		// CrudoFileWriter.writeEditPage(crudCreator, "rrhh", true);
		// CrudoFileWriter.writeListPage(crudCreator, "rrhh", true);

		assert (true);

//		Connection conn = null;
//		Properties connectionProps = new Properties();
//		connectionProps.put("user", "sa");
//		connectionProps.put("password", "");
//
//		try {
//			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9004/lotinfo", connectionProps);
//			DtoGenerator dtoGen = new DtoGenerator();
//			dtoGen.setPackageName("com.gt.lotinfo.web.dto");
//			dtoGen.setClassName("ResumenCuenta");
//			dtoGen.setConnection(conn);
//			dtoGen.setSqlQuery(ComprobanteService.CONSULTA_RESUMEN_CUENTA);
//			
//			System.out.println(dtoGen.generateDto());
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

}