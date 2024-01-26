package com.sintaxy.oodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sintaxy.oodo.exception.EntidadeEmUsoException;
import com.sintaxy.oodo.exception.EntidadeNaoEncontradaException;
import com.sintaxy.oodo.model.Utilizador;
import com.sintaxy.oodo.repository.UtilizadorRepository;

@Service
public class CadastroUtilizadorService {
	
	@Autowired
    private UtilizadorRepository utilizadorRepository;
	
	public Utilizador salvar(Utilizador utilizador) {
        
        return utilizadorRepository.save(utilizador);
    }

	
public void excluir(Long utilizadorId) {
		
		try {
			
			utilizadorRepository.deleteById(utilizadorId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de estado com código %d", utilizadorId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Estado de código %d não pode ser removida, pois está em uso", utilizadorId));
		}
	}
	
	
	
}
