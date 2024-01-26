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
import com.sintaxy.oodo.model.Sistema;
import com.sintaxy.oodo.repository.EmpresaRepository;
import com.sintaxy.oodo.repository.SistemaRepository;
import com.sintaxy.oodo.service.CadastroEmpresaService;
import com.sintaxy.oodo.service.CadastroSistemaService;

@RestController
@RequestMapping(value =  "/sistema")
public class SistemaController {
	
	@Autowired(required=true)
	private SistemaRepository sistemaRepository;
	
	@Autowired
	private CadastroSistemaService cadastroSistema;
	
	
	//Método usado para listar todos os sistemas
@GetMapping
@ResponseBody
  public List<Sistema> listar(){
		return sistemaRepository.findAll();
	}



//Método usado para salvar Sistema
	 @PostMapping
		public ResponseEntity<?> adicionar(@RequestBody Sistema sistema) {
			try {
				sistema = cadastroSistema.salvar(sistema);
				
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(sistema);
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.badRequest()
						.body(e.getMessage());
			}
		}

	 
	 
	 
	 //Metódo usado para buscar um sistema por Id
	 @GetMapping("/{sistemaId}")
		public ResponseEntity<Sistema> buscar(@PathVariable Long sistemaId) {
		 Optional<Sistema> sistema = sistemaRepository.findById(sistemaId);
			
			if (sistema.isPresent()) {
				
				return ResponseEntity.ok(sistema.get());
			}
			
			return ResponseEntity.notFound().build();
		}
	 
	 
	 
	 @PutMapping("/{sistemaId}")
		public ResponseEntity<Sistema> atualizar(@PathVariable Long sistemaId,
				@RequestBody Sistema sistema) {
			Optional<Sistema> sistemaAtual = sistemaRepository.findById(sistemaId);
			
			if (sistemaAtual.isPresent()) {
				
				BeanUtils.copyProperties(sistema, sistemaAtual.get(), "id");
				
				Sistema sistemaSlva = sistemaRepository.findById(sistemaId).orElse(null);
				
				return ResponseEntity.ok(sistemaSlva);
			}
			
			return ResponseEntity.notFound().build();
		}
	 
	 
	 
	//Método usado para eliminar um sistema por id
		 @DeleteMapping("/{sistemaId}")
			public ResponseEntity<?> remover(@PathVariable Long sistemaId) {
				try {
					cadastroSistema.excluir(sistemaId);	
					return ResponseEntity.noContent().build();
					
				} catch (EntidadeNaoEncontradaException e) {
					return ResponseEntity.notFound().build();
					
				} catch (EntidadeEmUsoException e) {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body(e.getMessage());
				}
			}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
