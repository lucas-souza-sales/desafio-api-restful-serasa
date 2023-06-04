package br.com.serasa.api.vendedores.model;

import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Atuacao {
	
	@Id
	@Column(name = "regiao")
	private String regiao;
	
	@Column(name = "estados", nullable = false)
	@ElementCollection(targetClass=String.class)
	private Set<String> estados;

}
