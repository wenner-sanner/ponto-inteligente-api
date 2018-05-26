package com.wenner.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wenner.pontointeligente.api.entities.Lancamento;
import com.wenner.pontointeligente.api.respositories.Lancamentos;

@RunWith (SpringRunner.class)
@SpringBootTest
@ActiveProfiles ("test")
public class LancamentoServiceTest {
	
	@MockBean
	private Lancamentos lancamentos;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(lancamentos.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)) )
					.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()) );		
		BDDMockito.given(lancamentos.getOne(Mockito.anyLong())).willReturn(new Lancamento() );
		BDDMockito.given(lancamentos.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento() );
	}
	
	@Test
	public void testBuscarLancamentoPorFuncionarioId() {
		
		Page<Lancamento> lancamento = lancamentoService.buscarPorFuncionarioId(1L, new PageRequest(0, 10) );
		
		assertNotNull(lancamento);
	}
	
	@Test
	public void testBuscarlancamentoPorId() {
		Optional<Lancamento> lancamento = lancamentoService.buscarPorId(1L);
		
		assertTrue(lancamento.isPresent() );
	}
	
	@Test
	public void testPersistirLancamento() {
		Lancamento lancamento = lancamentoService.persistir(new Lancamento() );
		
		assertNotNull(lancamento);
	}

}
