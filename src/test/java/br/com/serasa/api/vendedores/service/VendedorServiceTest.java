package br.com.serasa.api.vendedores.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.serasa.api.vendedores.dto.VendedorResponseAll;
import br.com.serasa.api.vendedores.dto.VendedorSingleResponse;
import br.com.serasa.api.vendedores.exceptions.VendedorNotFoundException;
import br.com.serasa.api.vendedores.model.Vendedor;
import br.com.serasa.api.vendedores.repository.VendedorRepository;
import br.com.serasa.api.vendedores.util.TestDataCreator;
import br.com.serasa.api.vendedores.util.TestConstants;

@SpringBootTest
public class VendedorServiceTest {

	@MockBean
	private ModelMapper modelMapper;

	@MockBean
	private VendedorRepository vendedorRepository;

	@MockBean
	private AtuacaoService atuacaoService;

	private VendedorService vendedorService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		vendedorService = new VendedorService(modelMapper, vendedorRepository, atuacaoService);
	}

	@Test
	void deveRetornarVendedorAoExecutarPostComSucesso() {
		var vendedor = TestDataCreator.createVendedor();
		var vendedorDTO = TestDataCreator.createVendedorDTO();

		when(modelMapper.map(vendedorDTO, Vendedor.class)).thenReturn(vendedor);
		when(vendedorRepository.save(vendedor)).thenReturn(vendedor);

		var vendedorCreated = vendedorService.create(vendedorDTO);

		assertEquals(vendedorCreated.getNome(), vendedorDTO.getNome());
		assertEquals(vendedorCreated.getCidade(), vendedorDTO.getCidade());
		assertEquals(vendedorCreated.getEstado(), vendedorDTO.getEstado());
		assertEquals(vendedorCreated.getIdade(), vendedorDTO.getIdade());
		assertEquals(vendedorCreated.getTelefone(), vendedorDTO.getTelefone());
		assertEquals(vendedorCreated.getRegiao(), vendedorDTO.getRegiao());
	}

	@Test
	void deveRetornarTodosVendedores() {
		var vendedor = TestDataCreator.createVendedor();
		var listaVendedores = Collections.singletonList(vendedor);

		when(vendedorRepository.findAll()).thenReturn(listaVendedores);
		when(atuacaoService.findByRegiao(vendedor.getRegiao())).thenReturn(TestDataCreator.createOptionalRegiaoAtuacao());
		when(modelMapper.map(vendedor, VendedorResponseAll.class))
				.thenReturn(TestDataCreator.createVendedorResponseAll());

		var vendedorResponseListAll = vendedorService.findAll();

		assertFalse(vendedorResponseListAll.isEmpty());
		assertEquals(1L, vendedorResponseListAll.size());
	}

	@Test
	void deveRetornarExceptionAoBuscarListENaoExistirVendedorCadastrado() {
		when(vendedorRepository.findAll()).thenReturn(List.of());
		assertThrows(VendedorNotFoundException.class, () -> vendedorService.findAll());
	}

	@Test
	void deveRetornarExceptionAoBuscarPessoaPorIdInexistente() {
		when(vendedorRepository.findById(42L)).thenReturn(Optional.empty());
		assertThrows(VendedorNotFoundException.class, () -> vendedorService.findById(42L));
	}

	@Test
	void deveRetornarVendedorIdOne() {
		var vendedor = TestDataCreator.createVendedor();

		when(vendedorRepository.findById(TestConstants.ID)).thenReturn(Optional.of(vendedor));
		when(atuacaoService.findByRegiao(Optional.of(vendedor).get().getRegiao()))
				.thenReturn(TestDataCreator.createOptionalRegiaoAtuacao());
		when(modelMapper.map(Optional.of(vendedor), VendedorSingleResponse.class))
				.thenReturn(TestDataCreator.createVendedorSingleResponse());

		var vendedorSingleResponse = vendedorService.findById(TestConstants.ID);

		assertEquals(vendedor.getNome(), vendedorSingleResponse.getNome());
		assertEquals(vendedor.getDataInclusao(), vendedorSingleResponse.getDataInclusao());
		assertEquals(TestConstants.ESTADOS, vendedorSingleResponse.getEstados());
	}

}
