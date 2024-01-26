package com.sintaxy.oodo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sintaxy.oodo.exception.EntidadeEmUsoException;
import com.sintaxy.oodo.exception.EntidadeNaoEncontradaException;
import com.sintaxy.oodo.model.Empresa;
import com.sintaxy.oodo.repository.EmpresaRepository;
import com.sintaxy.oodo.service.CadastroEmpresaService;

@RestController
@RequestMapping(value =  "/empresa")
public class EmpresaController {
	
	@Autowired(required=true)
	private EmpresaRepository empresaRepository;
	
	 @Autowired
		private CadastroEmpresaService cadastroEmpresa;
	

		//Método usado para listar todas as Empresa
	@GetMapping
	@ResponseBody
	  public List<Empresa> listar(){
			return empresaRepository.findAll();
		}
	
	
	
	

	 @PutMapping("/{empresaId}")
		public ResponseEntity<Empresa> atualizar(@PathVariable Long empresaId,
				@RequestBody Empresa empresa) {
			Optional<Empresa> empresaAtual = empresaRepository.findById(empresaId);
			
			if (empresaAtual.isPresent()) {
				
				BeanUtils.copyProperties(empresa, empresaAtual.get(), "id");
				
				Empresa empresaSlva = empresaRepository.findById(empresaId).orElse(null);
				
				return ResponseEntity.ok(empresaSlva);
			}
			
			return ResponseEntity.notFound().build();
		}
	 
	 
	 
	 
	 //Método usado para salvar Empresa
	 @PostMapping
		public ResponseEntity<?> adicionar(@RequestBody Empresa empresa) {
			try {
				empresa = cadastroEmpresa.salvar(empresa);
				
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(empresa);
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.badRequest()
						.body(e.getMessage());
			}
		}
	 
	 
	 
	 //Metódo usado para buscar uma empresa por Id
	 @GetMapping("/{empresaId}")
		public ResponseEntity<Empresa> buscar(@PathVariable Long empresaId) {
		 Optional<Empresa> empresa = empresaRepository.findById(empresaId);
			
			if (empresa.isPresent()) {
				
				return ResponseEntity.ok(empresa.get());
			}
			
			return ResponseEntity.notFound().build();
		}
	 
	 
	 
	 //Método usado para eliminar uma empresa por id
	 @DeleteMapping("/{empresaAId}")
		public ResponseEntity<?> remover(@PathVariable Long empresaId) {
			try {
				cadastroEmpresa.excluir(empresaId);	
				return ResponseEntity.noContent().build();
				
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.notFound().build();
				
			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(e.getMessage());
			}
		}

}
