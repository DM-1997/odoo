package com.sintaxy.oodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sintaxy.oodo.exception.EntidadeEmUsoException;
import com.sintaxy.oodo.exception.EntidadeNaoEncontradaException;
import com.sintaxy.oodo.model.Tickets;
import com.sintaxy.oodo.repository.TicketsRepository;

@Service
public class CadastroTicketsService {

	@Autowired
    private TicketsRepository ticketsRepository;
	
	public Tickets salvar(Tickets tickets) {
        
        return ticketsRepository.save(tickets);
    }
  
	
public void excluir(Long ticketsId) {
		
		try {
			
			ticketsRepository.deleteById(ticketsId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de estado com código %d", ticketsId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Estado de código %d não pode ser removida, pois está em uso", ticketsId));
		}
	}
	
}
