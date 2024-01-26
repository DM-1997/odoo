package com.sintaxy.oodo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sintaxy.oodo.exception.EntidadeEmUsoException;
import com.sintaxy.oodo.exception.EntidadeNaoEncontradaException;
import com.sintaxy.oodo.model.Empresa;
import com.sintaxy.oodo.model.Utilizador;
import com.sintaxy.oodo.repository.EmpresaRepository;
import com.sintaxy.oodo.repository.UtilizadorRepository;
import com.sintaxy.oodo.service.CadastroEmpresaService;
import com.sintaxy.oodo.service.CadastroUtilizadorService;

@RestController
@RequestMapping(value =  "/utilizador")
public class UtilizadoresController {

	@Autowired(required=true)
	private UtilizadorRepository utilizadorRepository;
	
	 @Autowired
		private CadastroUtilizadorService cadastroUtilizador;
	
	
	
	//Método usado para listar todos os utilizadores
		@GetMapping
		@ResponseBody
		  public List<Utilizador> listar(){
				return utilizadorRepository.findAll();
			}
	
	
		//Método usado para salvar um utilizador
		 @PostMapping
			public ResponseEntity<?> adicionar(@RequestBody Utilizador utilizador) {
				try {
					utilizador = cadastroUtilizador.salvar(utilizador);
					
					return ResponseEntity.status(HttpStatus.CREATED)
							.body(utilizador);
				} catch (EntidadeNaoEncontradaException e) {
					return ResponseEntity.badRequest()
							.body(e.getMessage());
				}
			}
	
	
	
	
		//Metódo usado para buscar um utilizador por Id
		 @GetMapping("/{utilizadorId}")
			public ResponseEntity<Utilizador> buscar(@PathVariable Long utilizadorId) {
			 Optional<Utilizador> utilizador = utilizadorRepository.findById(utilizadorId);
				
				if (utilizador.isPresent()) {
					
					return ResponseEntity.ok(utilizador.get());
				}
				
				return ResponseEntity.notFound().build();
			}
	
	
		 
		 
		 //Método usado para eliminar um utilizador por id
		 @DeleteMapping("/{utilizadorAId}")
			public ResponseEntity<?> remover(@PathVariable Long utilizadorId) {
				try {
					cadastroUtilizador.excluir(utilizadorId);	
					return ResponseEntity.noContent().build();
					
				} catch (EntidadeNaoEncontradaException e) {
					return ResponseEntity.notFound().build();
					
				} catch (EntidadeEmUsoException e) {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body(e.getMessage());
				}
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
