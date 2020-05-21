package com.everton.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.everton.cobranca.TitulosRepo;
import com.everton.cobranca.model.Titulo;

@Controller
@RequestMapping(value = "/titulos")
public class TituloController {
    
    @Autowired
    private TitulosRepo titulosRepository;

    //Return the name of the View
    @RequestMapping(value = "/novo")
    public String novo() {
        return "CadastroTitulo";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String salvar(Titulo tituloCob) {
        System.out.println(" ## Salvou titulo aqui = " + tituloCob.getDescricao());
        System.out.println(" ## Salvou titulo aqui = " + tituloCob.getStatus());
        System.out.println(" ## Salvou titulo aqui = " + tituloCob.getDataVencimento());
        System.out.println(" ## Salvou titulo aqui = " + tituloCob.getValor());
        
        titulosRepository.save(tituloCob);
        
        return "CadastroTitulo";
    }
    
    /*
     * Observacoes
     * Um POST em titulos, significa que vamos Salvar o Titulo   localhost:8080/titulos
     */
}
