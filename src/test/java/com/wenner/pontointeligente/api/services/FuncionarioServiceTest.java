package com.wenner.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wenner.pontointeligente.api.entities.Funcionario;
import com.wenner.pontointeligente.api.respositories.Funcionarios;

@RunWith (SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles ("test")
public class FuncionarioServiceTest {

	@MockBean
	private Funcionarios funcionarios;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(funcionarios.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario() );
		BDDMockito.given(funcionarios.getOne(Mockito.anyLong())).willReturn(new Funcionario() );
		BDDMockito.given(funcionarios.findByEmail(Mockito.anyString())).willReturn(new Funcionario() );
		BDDMockito.given(funcionarios.findByCpf(Mockito.anyString())).willReturn(new Funcionario() );
	}
	
	@Test
	public void testPersistirFuncioario() {
		Funcionario funcionario = funcionarioService.persistir(new Funcionario() );
		
		assertNotNull(funcionario);
	}
	
	@Test
	public void testBuscarFuncinarioPorID() {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorId(1L);
		
		assertNotNull(funcionario);
	}
	
	@Test 
	public void testBuscarFuncionarioPorEmail() {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorEmail("email@email.com");
		
		assertTrue( funcionario.isPresent() );
	}
	
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorCpf("111111111");
		
		assertTrue( funcionario.isPresent() );
	}
}
