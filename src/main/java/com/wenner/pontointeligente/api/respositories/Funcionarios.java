package com.wenner.pontointeligente.api.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.wenner.pontointeligente.api.entities.Funcionario;

@Transactional (readOnly = true)
public interface Funcionarios extends JpaRepository<Funcionario, Long> {

	Funcionario findByCpf(String cpf);
	
	Funcionario findByEmail(String email);
	
	Funcionario findByCpfOrEmail(String cpf, String email);
	
}
