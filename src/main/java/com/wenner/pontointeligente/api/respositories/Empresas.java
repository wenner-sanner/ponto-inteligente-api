package com.wenner.pontointeligente.api.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.wenner.pontointeligente.api.entities.Empresa;

public interface Empresas extends JpaRepository<Empresa, Long> {
	
	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);

}
