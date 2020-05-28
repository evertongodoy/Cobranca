package com.everton.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.everton.cobranca.model.StatusTitulo;
import com.everton.cobranca.model.Titulo;
import com.everton.cobranca.repository.TitulosRepo;
import com.everton.cobranca.repository.filter.TituloFilter;

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

	
	public String receberTitulo(Long codTituloReceber) {
		// Recuperando o Titulo que tem esse codigo
		Titulo titulo = titulosRepoService.getOne(codTituloReceber);   // findOne -> getOne
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulosRepoService.save(titulo);
		return StatusTitulo.RECEBIDO.getDescricao();
	}
	
	public List<Titulo> filtrarTit(TituloFilter flt){
        //List<Titulo> lstTit = titulosRepository.findAll();
    	String dsc = flt.getDescricao() == null ? "" : flt.getDescricao(); // Nao pode passar null
    	return titulosRepoService.findByDescricaoContaining(dsc);
	}
	
}
