package br.com.serasa.api.vendedores.dto;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO extends RepresentationModel<VendedorDTO> {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String telefone;
	
	@Min(1)
	private Integer idade;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
    @Size(max = 2, min = 2, message = "Formato deve ser -> 'XX'")
	private String estado;
	
	@NotBlank
	private String regiao;
	
}
