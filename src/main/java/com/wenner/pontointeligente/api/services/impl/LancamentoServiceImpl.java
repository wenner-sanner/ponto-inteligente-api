package com.wenner.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wenner.pontointeligente.api.entities.Lancamento;
import com.wenner.pontointeligente.api.respositories.Lancamentos;
import com.wenner.pontointeligente.api.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private Lancamentos lancamentos;

	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		log.info("Buscando lanacamentos para o funcionario de ID: {}", funcionarioId);
	
		return lancamentos.findByFuncionarioId(funcionarioId, pageRequest);
	}

	@Override
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando um lancamento pelo ID: {}", id);
		
		return Optional.ofNullable(lancamentos.getOne(id));
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo um lancamento: {}", lancamento);
		
		return lancamentos.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o lancamento de ID: {}", id);
		
		lancamentos.deleteById(id);		
	}

}
