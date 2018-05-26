package com.wenner.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wenner.pontointeligente.api.entities.Empresa;
import com.wenner.pontointeligente.api.entities.Funcionario;
import com.wenner.pontointeligente.api.enums.PerfilEnum;
import com.wenner.pontointeligente.api.respositories.Empresas;
import com.wenner.pontointeligente.api.respositories.Funcionarios;
import com.wenner.pontointeligente.api.utils.PasswordUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles ("test")
public class FuncionariosTest {
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Autowired
	private Empresas empresas;
	
	private static final String EMAIL = "wennersanner@hotmail.com";
	private static final String CPF = "99999999999";
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa = empresas.save(obterDadosEmpresa());
		funcionarios.save(obterDadosFuncionario(empresa));
	}

	@After
	public void tearDown() {
		empresas.deleteAll();
	}
	
	@Test
	public void testBuscarFuncionarioPorEmail() {
		Funcionario funcionario = funcionarios.findByEmail(EMAIL);
		
		assertEquals(EMAIL, funcionario.getEmail() );
	}
	
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario funcionario = funcionarios.findByCpf(CPF);
		
		assertEquals(CPF, funcionario.getCpf() );
	}
	
	@Test
	public void testBuscarFuncionarioPorCpfEmail() {
		Funcionario funcionario = funcionarios.findByCpfOrEmail(CPF, EMAIL);
		
		assertNotNull(funcionario);
	}
	
	@Test
	public void testBuscarFuncionarioPorCpfEmailParaCpfInvalido() {
		Funcionario funcionario = funcionarios.findByCpfOrEmail("333333", EMAIL);
		
		assertNotNull(funcionario);
	}
	
	@Test
	public void testBuscarFuncionarioPorCpfEmailParaEmailInvalido() {
		Funcionario funcionario = funcionarios.findByCpfOrEmail(CPF, "email@invalido.com");
		
		assertNotNull(funcionario);
	}
	
	private Funcionario obterDadosFuncionario(Empresa empresa) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Wenner Sanner");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("12345"));
		funcionario.setCpf(CPF);
		funcionario.setEmail(EMAIL);
		funcionario.setEmpresa(empresa);
		
		return funcionario;
		
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("WS tech");
		empresa.setCnpj("22222222");
		
		return empresa;
	}
	
}
