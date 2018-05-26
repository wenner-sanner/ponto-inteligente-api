package com.wenner.pontointeligente.api.services;

import java.util.Optional;

import com.wenner.pontointeligente.api.entities.Funcionario;

public interface FuncionarioService {
	
	/**
	 * Persiste um funcionario na base de dados
	 * 
	 * @param Funcionario	 * 
	 * @return Funcionario
	 * */
	Funcionario persistir(Funcionario funcionario);
	
	/**
	 * Busca e retorna um funcionario dado um cpf
	 * 
	 * @param cpf	 * 
	 * @return Optional<Funcionario>
	 * */
	Optional<Funcionario> buscarPorCpf(String cpf);

	/**
	 * Busca e retorna um funcionario dado um email
	 * 
	 * @param email
	 * @return Optional<FUncionario>
	 * */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 * Buscar e retorna um funcionario dado um id
	 * 
	 * @param id
	 * @return Optional<Funcionario>
	 * */
	Optional<Funcionario> buscarPorId(Long id);
}
