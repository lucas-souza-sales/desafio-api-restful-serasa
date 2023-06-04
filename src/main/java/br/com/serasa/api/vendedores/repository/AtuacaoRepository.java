package br.com.serasa.api.vendedores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.api.vendedores.model.Atuacao;

@Repository
public interface AtuacaoRepository extends JpaRepository<Atuacao, String> {
	
}
