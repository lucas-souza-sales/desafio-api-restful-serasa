package br.com.serasa.api.vendedores.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.serasa.api.vendedores.dto.AtuacaoDTO;
import br.com.serasa.api.vendedores.exceptions.AtuacaoNotFoundException;
import br.com.serasa.api.vendedores.model.Atuacao;
import br.com.serasa.api.vendedores.repository.AtuacaoRepository;
import br.com.serasa.api.vendedores.util.TestConstants;
import br.com.serasa.api.vendedores.util.TestDataCreator;

@SpringBootTest
public class AtuacaoServiceTest {
	
	@MockBean
	private ModelMapper modelMapper;
	
	@MockBean
	private AtuacaoRepository atuacaoRepository;
	
	private AtuacaoService atuacaoService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		atuacaoService = new AtuacaoService(modelMapper, atuacaoRepository);
	}
	
	@Test
	void deveRetornarAtuacaoAoExecutarPostComSucesso() {
		var atuacao = TestDataCreator.createRegiaoAtuacao();
		var atuacaoDTO = TestDataCreator.createRegiaoAtuacaoDTO();
		when(modelMapper.map(atuacaoDTO, Atuacao.class)).thenReturn(atuacao);
		when(atuacaoRepository.save(atuacao)).thenReturn(atuacao);
		
		var atuacaoCreated = atuacaoService.create(atuacaoDTO);
		
		assertEquals(atuacaoCreated.getRegiao(), atuacaoDTO.getRegiao());
		assertEquals(atuacaoCreated.getEstados(), atuacaoDTO.getEstados());
	}
	
	@Test
	void deveRetornarOptionalDeAtuacao() {		
		var atuacao = TestDataCreator.createRegiaoAtuacao();
		when(atuacaoRepository.findById(TestConstants.REGIAO)).thenReturn(Optional.of(atuacao));		
		var optionalRet = atuacaoService.findByRegiao(TestConstants.REGIAO);		
		assertEquals(Optional.of(atuacao), optionalRet);
	}
	
	@Test
	void deveRetornarAtuacaoDTOPelaRegiao() {		
		var atuacao = TestDataCreator.createRegiaoAtuacao();
		var atuacaoDto = TestDataCreator.createRegiaoAtuacaoDTO();
		when(atuacaoRepository.findById(TestConstants.REGIAO)).thenReturn(Optional.of(atuacao));	
		when(modelMapper.map(atuacao, AtuacaoDTO.class)).thenReturn(atuacaoDto);
		var optionalRet = atuacaoService.findById(TestConstants.REGIAO);		
		assertEquals(atuacaoDto, optionalRet);
	}
	
	@Test
	void deveRetornarTodosRegistrosDeAtuacaoDTOCadastrados() {		
		var atuacao = TestDataCreator.createRegiaoAtuacao();
		var listaCadastrados = Collections.singletonList(atuacao);
		var atuacaoDto = TestDataCreator.createRegiaoAtuacaoDTO();
		when(atuacaoRepository.findAll()).thenReturn(listaCadastrados);	
		when(modelMapper.map(atuacao, AtuacaoDTO.class)).thenReturn(atuacaoDto);
		var responseListAll = atuacaoService.findAll();	

		assertFalse(responseListAll.isEmpty());
		assertEquals(1L, responseListAll.size());	
	}
	
	@Test
	void deveRetornarExceptionAoBuscarPorRegiaoNaoCadastrada() {
		var regiao = "NORTE";
		when(atuacaoService.findByRegiao(regiao)).thenReturn(Optional.empty());
		assertThrows(AtuacaoNotFoundException.class, () -> atuacaoService.findById(regiao));
	}


}
