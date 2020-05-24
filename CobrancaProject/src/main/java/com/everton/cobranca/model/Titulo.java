package com.everton.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
public class Titulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados que controla
    private Long codigo;
    
    @NotBlank(message = "Informar uma descricao")
    @Size(max = 60, message = "Descricao pode ter ate 60 caracteres")
    private String descricao;
    
    // TemporalType.DATE, nao salva Hora Minutos Segundo.. Apenas o Data
    @NotNull(message = "Informe uma data de vencimento")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;
    
    @NotNull(message = "Informe um valor")
    @NumberFormat(pattern = "#,##0.00")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que R$ 0,00")
    @DecimalMax(value = "9999999.99", message = "O valor deve ser menor que R$ 10.000.000,00")
    private BigDecimal valor;
    
    @Enumerated(EnumType.STRING)  // EnumType.ORDINAL para Utilizar o Enum baseado em Numero
    private StatusTitulo status;
    
    
    
    // Getters and Setter No Mac -> Command+3, e digitar "generate g"
    
    
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Date getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public StatusTitulo getStatus() {
        return status;
    }
    public void setStatus(StatusTitulo status) {
        this.status = status;
    }
    
    
    public Boolean isPendente() {
        return StatusTitulo.PENDENTE.equals(this.status);
    }
    public Boolean isRecebido() {
        return StatusTitulo.RECEBIDO.equals(this.status);
    }
    
    
    
    // Hashcode and Equals No Mac -> Command+3, e digitar "ghc"
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Titulo other = (Titulo) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    
    
    
}
