package com.everton.cobranca;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everton.cobranca.model.Titulo;

public interface TitulosRepo extends JpaRepository<Titulo, Long> {

}
