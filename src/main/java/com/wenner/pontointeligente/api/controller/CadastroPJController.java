package com.wenner.pontointeligente.api.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenner.pontointeligente.api.dto.CadastroPJDto;
import com.wenner.pontointeligente.api.entities.Empresa;
import com.wenner.pontointeligente.api.entities.Funcionario;
import com.wenner.pontointeligente.api.enums.PerfilEnum;
import com.wenner.pontointeligente.api.response.Response;
import com.wenner.pontointeligente.api.services.EmpresaService;
import com.wenner.pontointeligente.api.services.FuncionarioService;
import com.wenner.pontointeligente.api.utils.PasswordUtils;

@RestController
@RequestMapping ("/api/cadastrar-pj")
@CrossOrigin (origins = "*")
public class CadastroPJController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	public CadastroPJController() { }

	/**
	 * Cadastra um pessoa juridica no sistema
	 * 
	 * @param CadastroPJDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPJDto>>
	 * @throws NoSuchAlgorithmException
	 * */
	
	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto,
						BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando um PJ: {}", cadastroPJDto.toString() );
		
		Response<CadastroPJDto> response = new Response<CadastroPJDto>();
		
		validarDadosExistentes(cadastroPJDto, result);
		Empresa empresa = converterDtoParaEmpresa(cadastroPJDto);
		Funcionario funcionario = converterDtoParaFuncionario(cadastroPJDto, result);
		
		if (result.hasErrors() ) {
			log.error("Erro validando dados cadastro PJ: {}", result.getAllErrors() );
			result.getAllErrors().forEach(error ->response.getErrors().add(error.getDefaultMessage() ) );
			return ResponseEntity.badRequest().body(response);
		}
		
		empresaService.persistir(empresa);	
		funcionario.setEmpresa(empresa);
		funcionarioService.persistir(funcionario);
		
		response.setData(converterCadastroPJDto(funcionario));
		
		return ResponseEntity.ok(response);
	}

	/**
	 * Popula DTO de cadastro com os dados do funcinarioe empresa
	 * 
	 * @param funcionario
	 * 
	 * @return cadastroPJDto
	 * **/
	private CadastroPJDto converterCadastroPJDto(Funcionario funcionario) {
		CadastroPJDto cadastroPJDto = new CadastroPJDto();
		cadastroPJDto.setId(funcionario.getId() );
		cadastroPJDto.setNome(funcionario.getNome() );
		cadastroPJDto.setEmail(funcionario.getEmail() );
		cadastroPJDto.setCpf(funcionario.getCpf() );
		cadastroPJDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial() );
		cadastroPJDto.setCnpj(funcionario.getEmpresa().getCnpj() );
		
		return cadastroPJDto;
	}

	/**
	 * Converte os dados do DTO para funcionario
	 * 
	 * @param cadastroPJDtp
	 * @param result
	 * @return funcionario
	 * @throws NoSuchAlgoritmException
	 * */
	private Funcionario converterDtoParaFuncionario(@Valid CadastroPJDto cadastroPJDto, BindingResult result) 
		throws NoSuchAlgorithmException {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPJDto.getNome() );
		funcionario.setEmail(cadastroPJDto.getEmail() );
		funcionario.setCpf(cadastroPJDto.getCpf() );
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN );
		funcionario.setSenha(PasswordUtils.gerarBCrypt(cadastroPJDto.getSenha() ) );
		
		return funcionario;
	}

	/**
	 * Converte os dados do DTO para empresa
	 * 
	 * @param cadastroPJDto
	 * 
	 * @return empresa
	 * **/
	private Empresa converterDtoParaEmpresa(@Valid CadastroPJDto cadastroPJDto) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPJDto.getCnpj() );
		empresa.setRazaoSocial(cadastroPJDto.getRazaoSocial() );
		
		return empresa;
	}

	/**
	 * Verifica se a empresa ou funcionario ja existem no banco de dados
	 * 
	 * @param CadastroPJDto
	 * @param result 
	 * */
	private void validarDadosExistentes(@Valid CadastroPJDto cadastroPJDto, BindingResult result) {
		empresaService.buscarPorCnpj(cadastroPJDto.getCnpj() )
		.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente") ) );
		
		funcionarioService.buscarPorCpf(cadastroPJDto.getCpf() )
		.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente") ) );
		
		funcionarioService.buscarPorEmail(cadastroPJDto.getEmail() )
		.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente") ) );
		
	}
	
}
