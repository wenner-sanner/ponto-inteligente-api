package com.wenner.pontointeligente.api.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenner.pontointeligente.api.dto.EmpresaDTo;
import com.wenner.pontointeligente.api.entities.Empresa;
import com.wenner.pontointeligente.api.response.Response;
import com.wenner.pontointeligente.api.services.EmpresaService;

@RestController
@RequestMapping ("/api/empresas")
@CrossOrigin (origins = "*")
public class EmpresaController {
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	public EmpresaController() {
		
	}
	
	/**
	 * Retorna uma empresa dado um cnpj
	 * 
	 * @param cnpj
	 * @return empresa
	 * */
	@GetMapping (value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDTo>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
		
		log.info("Buscando empresa por CNPJ: {}", cnpj);
		
		Response<EmpresaDTo> response = new Response<EmpresaDTo>();
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(cnpj);
		
		if (!empresa.isPresent() ) {
			log.info("Empresa não encontrada para o CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não encontrada para o CNPJ " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(converterEmpresaDtpo(empresa.get() ) );
		
		return ResponseEntity.ok(response);
	}

	
	/**
	 * Popula um DTO com os dadosda empresa
	 * 
	 * @param empresa
	 *@return empresaDto
	 * */
	private EmpresaDTo converterEmpresaDtpo(Empresa empresa) {
		EmpresaDTo empresaDTo = new EmpresaDTo();
		empresaDTo.setId(empresa.getId() );
		empresaDTo.setRazaoSocial(empresa.getRazaoSocial() );
		empresaDTo.setCnpj(empresa.getCnpj() );
		
		return empresaDTo;
	}
	
	
}
