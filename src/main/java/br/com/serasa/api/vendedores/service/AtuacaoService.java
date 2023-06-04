package br.com.serasa.api.vendedores.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.api.vendedores.controller.AtuacaoController;
import br.com.serasa.api.vendedores.dto.AtuacaoDTO;
import br.com.serasa.api.vendedores.exceptions.AtuacaoNotFoundException;
import br.com.serasa.api.vendedores.model.Atuacao;
import br.com.serasa.api.vendedores.repository.AtuacaoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AtuacaoService {
	
	public static final String MESSAGE_NOT_FOUND = "Região de atuação não encontrada!";
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AtuacaoRepository atuacaoRepository;
	
	public AtuacaoService(ModelMapper modelMapper, AtuacaoRepository atuacaoRepository) {
		super();
		this.modelMapper = modelMapper;
		this.atuacaoRepository = atuacaoRepository;
	}

	public Atuacao create(AtuacaoDTO atuacaoDTO) {
		var atuacao = modelMapper.map(atuacaoDTO, Atuacao.class);
		return atuacaoRepository.save(atuacao);
	}
	
	public Optional<Atuacao> findByRegiao(String regiao) {
		var atuacao = atuacaoRepository.findById(regiao);
        return atuacao;
	}
	
	public AtuacaoDTO findById(String regiao) {
		var atuacao = atuacaoRepository.findById(regiao);
		
		if (!atuacao.isPresent()) {
			throw new AtuacaoNotFoundException(MESSAGE_NOT_FOUND);
		}

		var atuacaoDto = modelMapper.map(atuacao.get(), AtuacaoDTO.class);
		atuacaoDto.add(linkTo(methodOn(AtuacaoController.class).listarTodos()).withRel("lista"));
		
        return atuacaoDto;
	}
	
	public List<AtuacaoDTO> findAll() {
		var listaAtuacoes = atuacaoRepository.findAll();
		
		if (listaAtuacoes.isEmpty()) {
			throw new AtuacaoNotFoundException(MESSAGE_NOT_FOUND);
		}
		
		return listaAtuacoes.stream().map(atuacao -> modelMapper.map(atuacao, AtuacaoDTO.class)
				.add(linkTo(methodOn(AtuacaoController.class).buscarPorRegiao(atuacao.getRegiao())).withSelfRel()))
				.collect(Collectors.toList());
		
	}

}
