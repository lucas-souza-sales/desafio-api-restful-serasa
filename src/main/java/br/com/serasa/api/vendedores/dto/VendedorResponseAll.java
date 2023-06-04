package br.com.serasa.api.vendedores.dto;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class VendedorResponseAll extends RepresentationModel<VendedorResponseAll>{
	
	private String nome;
	
	private String telefone;
	
	private Integer idade;
	
	private String cidade;
	
	private String estado;
	
	Set<String> estados;
	
}
