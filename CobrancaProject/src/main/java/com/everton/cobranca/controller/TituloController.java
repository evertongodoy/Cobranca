package com.everton.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TituloController {

    //Return the name of the View
    @RequestMapping("/titulos/novo")
    public String novo() {
        return "CadastroTitulo";
    }
    
}
