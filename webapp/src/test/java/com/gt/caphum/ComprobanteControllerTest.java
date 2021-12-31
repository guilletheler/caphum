package com.gt.caphum;

import com.gt.caphum.web.AdminBootApplication;

import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = { AdminBootApplication.class })
@AutoConfigureMockMvc
public class ComprobanteControllerTest {

	// @Autowired
	// private MockMvc mvc;

	// @Autowired
	// private ComprobanteService comprobanteService;

	// @Autowired
	// private FichaCuentaCorrienteService fichaCuentaCorrienteService;

	// write test cases here

	@Test
	public void testCcEditController() {
//	    	fichaCuentaCorrienteService.getRepo().findAll();
	}

	@Test
	public void testComprobanteListController() {
//	    	comprobanteService.getRepo().findAll();
//	    	comprobanteService.getNextNumero(1, TipoComprobante.LIQUIDACION_PRESCRIPTOS);

	}

}