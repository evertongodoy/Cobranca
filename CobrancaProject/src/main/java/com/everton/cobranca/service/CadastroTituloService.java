package com.everton.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.everton.cobranca.TitulosRepo;
import com.everton.cobranca.model.Titulo;

// Teria que ser no minimo como @Component, mas no caso, agora sera uma classe de Servico, por isso @Service

@Service
public class CadastroTituloService {

	@Autowired
	private TitulosRepo titulosRepoService;
	
	
	public void salvarTitulo(Titulo tit) {
		
		try {
			titulosRepoService.save(tit);
		} catch (DataIntegrityViolationException e) {  // Essa classe faz parte do pacote Spring Transaction, eh mais baixo nivel
			throw new IllegalArgumentException("Formato da Data esta errado"); // essa classe eh mais alto nvel
		}
		
	}

	public void excluirTitulo(Long codigo) {
		titulosRepoService.deleteById(codigo);
		
	}
	
}
