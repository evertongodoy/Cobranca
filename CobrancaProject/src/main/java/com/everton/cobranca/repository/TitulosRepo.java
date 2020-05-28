package com.everton.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everton.cobranca.model.Titulo;

public interface TitulosRepo extends JpaRepository<Titulo, Long> {
	
	public List<Titulo> findByDescricaoContaining(String ds);

}
