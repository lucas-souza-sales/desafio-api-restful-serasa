package br.com.serasa.api.vendedores.dto;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AtuacaoDTO  extends RepresentationModel<AtuacaoDTO> {
		
	@NotBlank(message = "Deve ser informado a regiao")
	String regiao;
	
	@NotNull(message = "Deve ser informado os estados")
	Set<String> estados;

}
