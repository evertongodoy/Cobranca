package com.everton.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.everton.cobranca.TitulosRepo;
import com.everton.cobranca.model.StatusTitulo;
import com.everton.cobranca.model.Titulo;
import com.everton.cobranca.service.CadastroTituloService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/titulos")
public class TituloController {
    
    private static final String CADASTRO_VIEW = "CadastroTitulo";
    
    @Autowired
    private TitulosRepo titulosRepository;
    
    @Autowired
    private CadastroTituloService cadastroTituloService;

    //Return the name of the View
    // @RequestMapping(value = "/novo")
    @GetMapping(path = "/novo")
    public ModelAndView novo() {
        
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
        mv.addObject("lstStatusTitulo", StatusTitulo.values()); // Return an Array of Status Titulo
        mv.addObject(new Titulo()); // TEM QUE SER O OBJETO, SEM UM NOME STRING, CASO CONTRARIO NA PAGINA, A TAG th:object="${titulo}"> NAO SERA RECONHECIDA
        
        return mv;
    }
    
    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public String salvar(@Validated Titulo tituloCob, Errors err, RedirectAttributes attributes) {
        if(err.hasErrors()) {
            return CADASTRO_VIEW;
        }
        
        try {
        	// Removida na aula 3.4. Camada de Servico e trocada por cadastroTituloService.salvarTitulo()...
        	//titulosRepository.save(tituloCob);
        	cadastroTituloService.salvarTitulo(tituloCob);
        	
            attributes.addFlashAttribute("msg", "Titulo salvo corretamente");
            return "redirect:/titulos/novo";
		} catch (IllegalArgumentException e) {
			err.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
        
    }
    
    // Deixar um atributo disponivel para as paginas da view 
    @ModelAttribute(value = "lstStatusTitulo")
    public List<StatusTitulo> retrieveStatusTitulo() {
        return Arrays.asList(StatusTitulo.values());
    }
    
    
    //@RequestMapping
    @GetMapping()
    public ModelAndView pesquisar() {
        
        List<Titulo> lstTit = titulosRepository.findAll();
        
        ModelAndView mv = new ModelAndView("PesquisaTitulos");
        mv.addObject("lstTitulo", lstTit);
        
        return mv;
    }
    
    @GetMapping(path = "/{codigoTitulo}")
    public ModelAndView editar(@PathVariable Long codigoTitulo) {
        Titulo titulo = titulosRepository.getOne(codigoTitulo);
        
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
        mv.addObject(titulo); 
        return mv;
    }

    // Esse video parece que pode ajudar, sera interessante
    // https://www.youtube.com/watch?v=o45S3j7MUPE
    
    //@DeleteMapping(value = "/{codigo}")
    //@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
    //@DeleteMapping("/{codigo}")
    /// @PostMapping(value = "/del/{codigo}")
    
    // Utilizando o @RestController, posso utilizar tanto no Postman, quanto aqui na aplicacao por POST
    @RequestMapping(value="/del/{codigo}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
        System.out.println("tentei excluir aqui... caramba " + codigo);
        
        // // Removida na aula 3.4. Camada de Servico e trocada por cadastroTituloService.excluirTitulo()...
        // titulosRepository.deleteById(codigo);  // Nesse exemplo, ele buusca pelo ID e depois executa o DELETE
        cadastroTituloService.excluirTitulo(codigo);
        
        attributes.addFlashAttribute("msg", "Titulo excluido corretamente,");
        return "redirect:/titulos"; // O redirect, faz o GET normal, e vai cair em pesquisar(), que faz a query de pesquisa novamente
    }
    
    // @ResponseBody, o Spring entende que voce nao quer retornar uma View, mas uma String como o corpo da resposta
    @RequestMapping(value = "/{codTituloReceber}/receber", method = RequestMethod.PUT)
    public @ResponseBody String receber(@PathVariable Long codTituloReceber) { // Vai receber um codTituloReceber que esta no PathVariable, o codigo dessa linha vai ser o codigo da linha de cima
    	//System.out.println(" >>> Codigo " + codTituloReceber);
    	return cadastroTituloService.receberTitulo(codTituloReceber);
    }
    
    /*
     * Observacoes
     * Um POST em titulos, significa que vamos Salvar o Titulo   localhost:8080/titulos
     */
}
