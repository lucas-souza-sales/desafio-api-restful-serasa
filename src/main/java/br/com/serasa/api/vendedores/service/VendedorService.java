package br.com.serasa.api.vendedores.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.api.vendedores.controller.VendedorController;
import br.com.serasa.api.vendedores.dto.VendedorDTO;
import br.com.serasa.api.vendedores.dto.VendedorResponseAll;
import br.com.serasa.api.vendedores.dto.VendedorSingleResponse;
import br.com.serasa.api.vendedores.exceptions.VendedorNotFoundException;
import br.com.serasa.api.vendedores.model.Vendedor;
import br.com.serasa.api.vendedores.repository.VendedorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VendedorService  {
	
	public static final String MESSAGE_NOT_FOUND = "Vendedor não encontrado!";
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private AtuacaoService atuacaoService;
	
	
	public VendedorService(ModelMapper modelMapper, VendedorRepository vendedorRepository,
			AtuacaoService atuacaoService) {
		this.modelMapper = modelMapper;
		this.vendedorRepository = vendedorRepository;
		this.atuacaoService = atuacaoService;
	}
	
	public Vendedor create(VendedorDTO vendedorDTO) {
		var vendedor = modelMapper.map(vendedorDTO, Vendedor.class);
		return vendedorRepository.save(vendedor);
	}

	public List<VendedorResponseAll> findAll() {
		var vendedores = vendedorRepository.findAll();
		
		if (vendedores.isEmpty()) {
			throw new VendedorNotFoundException(MESSAGE_NOT_FOUND);
		}
		
		vendedores.forEach(vendedor ->{
			var atuacao = atuacaoService.findByRegiao(vendedor.getRegiao());
			vendedor.setEstados((atuacao.isPresent() ? atuacao.get().getEstados() : Collections.emptySet()));
		});
		
		return vendedores.stream().map(vendedor -> modelMapper.map(vendedor, VendedorResponseAll.class)
				.add(linkTo(methodOn(VendedorController.class).buscarPorId(vendedor.getId())).withSelfRel()))
				.collect(Collectors.toList());
		
	}
	
	public VendedorSingleResponse findById(Long id) {
		
		var vendedor = vendedorRepository.findById(id);
		
		if (!vendedor.isPresent()) {
			throw new VendedorNotFoundException(MESSAGE_NOT_FOUND);
		}
		
		var atuacao = atuacaoService.findByRegiao(vendedor.get().getRegiao());
		vendedor.get().setEstados((atuacao.isPresent() ? atuacao.get().getEstados() : Collections.emptySet()));
		
		var vendedorSingleResponse = modelMapper.map(vendedor, VendedorSingleResponse.class)
				.add(linkTo(methodOn(VendedorController.class).listarTodos()).withRel("lista"));
		
		return vendedorSingleResponse;
	}

}
