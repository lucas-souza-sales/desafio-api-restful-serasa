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

import br.com.serasa.api.vendedores.dto.VendedorDTO;
import br.com.serasa.api.vendedores.dto.VendedorResponseAll;
import br.com.serasa.api.vendedores.dto.VendedorSingleResponse;
import br.com.serasa.api.vendedores.model.Vendedor;
import br.com.serasa.api.vendedores.service.VendedorService;
import jakarta.validation.Valid;


@RequestMapping("/vendedor")
@RestController
public class VendedorController {
	
	private final Logger logger = LoggerFactory.getLogger(Vendedor.class);
	
	@Autowired
	private final VendedorService vendedorService;

	public VendedorController(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}
	
	@PostMapping
	public ResponseEntity<VendedorDTO> salvar(@RequestBody @Valid VendedorDTO vendedorDTO, UriComponentsBuilder uriComponentsBuilder) {
		logger.info("requição post: salvar vendedor {}", vendedorDTO);
		var saved = vendedorService.create(vendedorDTO);
		var uri = uriComponentsBuilder.path("vendedor/{id}").buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	
	}
	
	@GetMapping
	public ResponseEntity<List<VendedorResponseAll>> listarTodos() {
		logger.info("requisição get: listar todos");		
		return ResponseEntity.ok(vendedorService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VendedorSingleResponse> buscarPorId(@PathVariable Long id) {
		logger.info("requisição get: buscar por id");
		var vendedor = vendedorService.findById(id);
		return ResponseEntity.ok(vendedor);
	}

}
