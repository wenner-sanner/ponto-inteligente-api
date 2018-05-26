package com.wenner.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenner.pontointeligente.api.entities.Empresa;
import com.wenner.pontointeligente.api.respositories.Empresas;
import com.wenner.pontointeligente.api.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);
	
	@Autowired
	private Empresas empresas;

	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ", cnpj);
		
		return Optional.ofNullable(empresas.findByCnpj(cnpj) );
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo uma empreda: {}", empresa);
		
		return empresas.save(empresa);
	}

}
