package com.everton.cobranca;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

// Adicionei o extends """ Para subir no Tomcat externo """
// extends SpringBootServletInitializer

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer{
    
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver(){
	    return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	// Para uma pagina de login, eu posso configurar um outro Controller
	// Nesse exemplo, se digitar localhost:8080/, vai direcionar para o /titulos
	// th:xxx="@{...}, o @ vai resolver as questoes de Context Path
	// - Criou uma classe estatica
	// - Estendeu de WebMvcConfigurerAdapter
	// - E adicionou um Redirefaf
	@Configuration
	public static class MvcConfig extends WebMvcConfigurerAdapter{
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/", "/titulos");
		}
	}
}