package br.com.serasa.api.vendedores.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.serasa.api.vendedores.dto.AtuacaoDTO;
import br.com.serasa.api.vendedores.model.Atuacao;
import br.com.serasa.api.vendedores.service.AtuacaoService;
import jakarta.validation.Valid;

@RequestMapping("/atuacao")
@RestController
public class AtuacaoController {
	
	private final Logger logger = LoggerFactory.getLogger(Atuacao.class);
	
	@Autowired
	private AtuacaoService atuacaoService;
	
	
	@PostMapping
	public ResponseEntity<AtuacaoDTO> salvar(@RequestBody @Valid AtuacaoDTO atuacaoDTO, UriComponentsBuilder uriComponentsBuilder) {
		logger.info("requição post: salvar vendedor {}", atuacaoDTO);
		var saved = atuacaoService.create(atuacaoDTO);
		var uri = uriComponentsBuilder.path("atuacao/{regiao}").buildAndExpand(saved.getRegiao()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@GetMapping
	public ResponseEntity<List<AtuacaoDTO>> listarTodos() {
		logger.info("requisição get: listar todos");		
		return ResponseEntity.ok(atuacaoService.findAll());
	}
	
	@GetMapping("/{regiao}")
	public ResponseEntity<AtuacaoDTO> buscarPorRegiao(@PathVariable String regiao) {
		logger.info("requisição get: buscar por regiao");
		var atuacao = atuacaoService.findById(regiao);
		return ResponseEntity.ok(atuacao);
	}

}
