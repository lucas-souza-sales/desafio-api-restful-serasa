package br.com.serasa.api.vendedores.model;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Vendedor  {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@CreatedDate
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_inclusao")
	private LocalDate dataInclusao;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "idade")
	private Integer idade;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "regiao")
	private String regiao;
	
	@Transient
	Set<String> estados; 

}
