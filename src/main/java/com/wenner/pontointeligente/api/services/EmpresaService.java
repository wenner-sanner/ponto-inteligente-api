package com.wenner.pontointeligente.api.services;

import java.util.Optional;

import com.wenner.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

	/**
	 * Retorna uma empresa dado um CNPJ
	 * 
	 * @param cnpj
	 * 
	 * @return Option<Empresa>
	 * */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa no banco de dados
	 * 
	 * @param Empresa
	 * 
	 * @return empresa
	 * */
	Empresa persistir(Empresa empresa);
}
