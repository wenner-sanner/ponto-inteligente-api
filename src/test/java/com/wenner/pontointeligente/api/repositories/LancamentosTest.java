package com.wenner.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wenner.pontointeligente.api.entities.Empresa;
import com.wenner.pontointeligente.api.entities.Funcionario;
import com.wenner.pontointeligente.api.entities.Lancamento;
import com.wenner.pontointeligente.api.enums.PerfilEnum;
import com.wenner.pontointeligente.api.enums.TipoEnum;
import com.wenner.pontointeligente.api.respositories.Empresas;
import com.wenner.pontointeligente.api.respositories.Funcionarios;
import com.wenner.pontointeligente.api.respositories.Lancamentos;
import com.wenner.pontointeligente.api.utils.PasswordUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles ("test")
public class LancamentosTest {
	
	@Autowired
	private Lancamentos lancamentos;

	@Autowired
	private Funcionarios funcionarios;

	@Autowired
	private Empresas empresas;
	
	private Long funcionarioId;
	
	@Before
	public void setUp() throws Exception {
		
		Empresa empresa = empresas.save(obterDadosEmpresa() );
		
		Funcionario funcionario = funcionarios.save(obterDadosFuncionario(empresa) );
		funcionarioId = funcionario.getId();
		
		lancamentos.save(obterDadosLancamento(funcionario) );
		lancamentos.save(obterDadosLancamento(funcionario) );
	}
	
	@After
	public void teraDown() throws Exception{
		empresas.deleteAll();
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioId() {
		
		List<Lancamento> lancamentosID = lancamentos.findByFuncionarioId(funcionarioId);
		
		assertEquals(2, lancamentosID.size() );		
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioIDPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Lancamento> lancametosPaginados = lancamentos.findByFuncionarioId(funcionarioId, page);
		
		assertEquals(2, lancametosPaginados.getTotalElements() );
	}
	
	private Lancamento obterDadosLancamento(Funcionario funcionario) {
		Lancamento lancamento = new Lancamento();
		lancamento.setData(new Date() );
		lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
		lancamento.setFuncionario(funcionario);
		
		return lancamento;
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Wenner Sanner");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("12345") );
		funcionario.setCpf("9999999-99");
		funcionario.setEmail("wennersanner@email.com");
		funcionario.setEmpresa(empresa);
		
		return funcionario;
	}
	
	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("WS tech");
		empresa.setCnpj("1234567");
		
		return empresa;
	}

	
}
