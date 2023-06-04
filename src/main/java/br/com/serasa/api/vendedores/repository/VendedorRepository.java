package br.com.serasa.api.vendedores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.api.vendedores.model.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

}
