package br.com.serasa.api.vendedores.dto;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class VendedorSingleResponse extends RepresentationModel<VendedorSingleResponse>{
	
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInclusao;
	
	Set<String> estados;
	
}
