package com.wenner.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenner.pontointeligente.api.entities.Funcionario;
import com.wenner.pontointeligente.api.respositories.Funcionarios;
import com.wenner.pontointeligente.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private Funcionarios funcionarios;

	@Override
	public Funcionario persistir(Funcionario funcionario) {
		log.info("Persistinfo funcionario: {}", funcionario);
		
		return funcionarios.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando funcionario pelo cpf: {}", cpf);
		
		return Optional.ofNullable(funcionarios.findByCpf(cpf) );
	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando funcionario pelo email: {}", email);
		
		return Optional.ofNullable(funcionarios.findByEmail(email) );
	}

	@Override
	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando funcionario pelo id: {}", id);
		
		return Optional.ofNullable(funcionarios.getOne(id) );
	}


}
