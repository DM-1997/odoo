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
import com.sintaxy.oodo.model.Tickets;
import com.sintaxy.oodo.repository.EmpresaRepository;
import com.sintaxy.oodo.repository.TicketsRepository;
import com.sintaxy.oodo.service.CadastroEmpresaService;
import com.sintaxy.oodo.service.CadastroTicketsService;

@RestController
@RequestMapping(value =  "/tickets")
public class TicketsController {

	
	@Autowired(required=true)
	private TicketsRepository ticketsRepository;
	
	@Autowired
	private CadastroTicketsService cadastroTickets;
	
	
	//Método usado para listar todos  tickets
      @GetMapping
      @ResponseBody
  public List<Tickets> listar(){
		return ticketsRepository.findAll();
	}
	
	
      //Método usado para salvar TICKETS
 	 @PostMapping
 		public ResponseEntity<?> adicionar(@RequestBody Tickets tickets) {
 			try {
 				tickets = cadastroTickets.salvar(tickets);
 				
 				return ResponseEntity.status(HttpStatus.CREATED)
 						.body(tickets);
 			} catch (EntidadeNaoEncontradaException e) {
 				return ResponseEntity.badRequest()
 						.body(e.getMessage());
 			}
 		}
	
 	 
 	 
	
	 @PutMapping("/{ticketsId}")
		public ResponseEntity<Tickets> atualizar(@PathVariable Long ticketsId,
				@RequestBody Tickets tickets) {
			Optional<Tickets> ticketsAtual = ticketsRepository.findById(ticketsId);
			
			if (ticketsAtual.isPresent()) {
				
				BeanUtils.copyProperties(tickets, ticketsAtual.get(), "id");
				
				Tickets ticketsSlva = ticketsRepository.findById(ticketsId).orElse(null);
				
				return ResponseEntity.ok(ticketsSlva);
			}
			
			return ResponseEntity.notFound().build();
		}
	
	 
	 
	
 	 //Metódo usado para buscar TICKETS por Id
	 @GetMapping("/{ticketsId}")
		public ResponseEntity<Tickets> buscar(@PathVariable Long ticketsId) {
		 Optional<Tickets> tickets = ticketsRepository.findById(ticketsId);
			
			if (tickets.isPresent()) {
				
				return ResponseEntity.ok(tickets.get());
			}
			
			return ResponseEntity.notFound().build();
		}
	 
	 
	 
	 //Método usado para eliminar um tickets por id
	 @DeleteMapping("/{ticketsId}")
		public ResponseEntity<?> remover(@PathVariable Long ticketsId) {
			try {
				cadastroTickets.excluir(ticketsId);	
				return ResponseEntity.noContent().build();
				
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.notFound().build();
				
			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(e.getMessage());
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
