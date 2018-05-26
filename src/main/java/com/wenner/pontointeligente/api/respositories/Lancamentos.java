package com.wenner.pontointeligente.api.respositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wenner.pontointeligente.api.entities.Lancamento;

@Transactional (readOnly = true)
@NamedQueries ({
					@NamedQuery (name = "Lancamentos.findByFuncionarioId",
								query = "SELECT l FROM Lncamento l"
										+ "WHERE l.funcionario.id = :funcionario.id") 	})
public interface Lancamentos extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId);
	
	Page<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId, Pageable pageable);
}
