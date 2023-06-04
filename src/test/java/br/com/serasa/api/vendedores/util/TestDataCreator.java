package br.com.serasa.api.vendedores.util;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import br.com.serasa.api.vendedores.dto.AtuacaoDTO;
import br.com.serasa.api.vendedores.dto.VendedorDTO;
import br.com.serasa.api.vendedores.dto.VendedorResponseAll;
import br.com.serasa.api.vendedores.dto.VendedorSingleResponse;
import br.com.serasa.api.vendedores.model.Atuacao;
import br.com.serasa.api.vendedores.model.Vendedor;

public final class TestDataCreator {

	private TestDataCreator() { }
	
	public static Vendedor createVendedor() {
		return Vendedor.builder()
				.id(TestConstants.ID)
				.dataInclusao(TestConstants.DATA_INCLUSAO)
				.nome(TestConstants.NOME)
				.telefone(TestConstants.TELEFONE)
				.idade(TestConstants.IDADE)
				.cidade(TestConstants.CIDADE)
				.estado(TestConstants.ESTADO)
				.regiao(TestConstants.REGIAO).build();
	}
	
	public static VendedorResponseAll createVendedorResponseAll() {
        ModelMapper mapper = new ModelMapper();
        var vendedor = createVendedor();     
        vendedor.setEstados(TestConstants.ESTADOS);    
        return mapper.map(vendedor, VendedorResponseAll.class);
    }
	
	public static VendedorSingleResponse createVendedorSingleResponse() {
        ModelMapper mapper = new ModelMapper();
        var vendedor = createVendedor();
        vendedor.setEstados(TestConstants.ESTADOS);    
        return mapper.map(vendedor, VendedorSingleResponse.class);
    }
	
	public static VendedorDTO createVendedorDTO() {
        return new VendedorDTO(TestConstants.NOME, TestConstants.TELEFONE, TestConstants.IDADE, 
        		TestConstants.CIDADE, TestConstants.ESTADO, TestConstants.REGIAO);
    }
	
	public static Optional<Atuacao> createOptionalRegiaoAtuacao() {
		return Optional.of(new Atuacao(TestConstants.REGIAO, TestConstants.ESTADOS));
	}
	
	public static Atuacao createRegiaoAtuacao() {
		return new Atuacao(TestConstants.REGIAO, TestConstants.ESTADOS);
	}
	
	public static AtuacaoDTO createRegiaoAtuacaoDTO() {
		return new AtuacaoDTO(TestConstants.REGIAO, TestConstants.ESTADOS);
	}
}
