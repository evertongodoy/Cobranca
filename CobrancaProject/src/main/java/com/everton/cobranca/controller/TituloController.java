package com.everton.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.everton.cobranca.TitulosRepo;
import com.everton.cobranca.model.StatusTitulo;
import com.everton.cobranca.model.Titulo;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/titulos")
public class TituloController {
    
    @Autowired
    private TitulosRepo titulosRepository;

    //Return the name of the View
    // @RequestMapping(value = "/novo")
    @GetMapping(value = "/novo")
    public ModelAndView novo() {
        
        ModelAndView mv = new ModelAndView("CadastroTitulo");
        mv.addObject("lstStatusTitulo", StatusTitulo.values()); // Return an Array of Status Titulo
        
        return mv;
    }
    
    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ModelAndView salvar(Titulo tituloCob) {
        System.out.println(" ## Salvou titulo aqui = " + tituloCob.getDescricao());
        System.out.println(" ## Salvou titulo aqui = " + tituloCob.getStatus());
        System.out.println(" ## Salvou titulo aqui = " + tituloCob.getDataVencimento());
        System.out.println(" ## Salvou titulo aqui = " + tituloCob.getValor());
        
        ModelAndView mv = new ModelAndView("CadastroTitulo");
        mv.addObject("msg", "Titulo salvo corretamente");
        
        titulosRepository.save(tituloCob);
        
        return mv;
    }
    
    // Deixar um atributo disponivel para as paginas da view 
    @ModelAttribute(value = "lstStatusTitulo")
    public List<StatusTitulo> retrieveStatusTitulo() {
        return Arrays.asList(StatusTitulo.values());
    }
    
    
    //@RequestMapping
    @GetMapping
    public ModelAndView pesquisar() {
        
        List<Titulo> lstTit = titulosRepository.findAll();
        
        ModelAndView mv = new ModelAndView("PesquisaTitulos");
        mv.addObject("lstTitulo", lstTit);
        
        return mv;
    }
    
    /*
     * Observacoes
     * Um POST em titulos, significa que vamos Salvar o Titulo   localhost:8080/titulos
     */
}
