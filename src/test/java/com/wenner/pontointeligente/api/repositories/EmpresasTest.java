package com.wenner.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wenner.pontointeligente.api.entities.Empresa;
import com.wenner.pontointeligente.api.respositories.Empresas;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresasTest {
	
	@Autowired
	private Empresas empresas;
	
	private static final String CNPJ = "51463645000100";

	@Before
	public void setUp() throws Exception {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setCnpj(CNPJ);
		empresas.save(empresa);
	}
	
	@After
    public final void tearDown() { 
		this.empresas.deleteAll();
	}

	@Test
	public void testBuscarPorCnpj() {
		Empresa empresa = empresas.findByCnpj(CNPJ);
		
		assertEquals(CNPJ, empresa.getCnpj());
	}

}