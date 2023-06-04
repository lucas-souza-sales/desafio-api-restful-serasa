package br.com.serasa.api.vendedores.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public final class TestConstants {
	
	
	public static final Long ID = 1L;
	public static final LocalDate DATA_INCLUSAO = LocalDate.of(2023, 6, 3);
    public static final String NOME = "LUCAS";
    public static final String TELEFONE = "(88) 9 9989-0181";
    public static final Integer IDADE = 32;
    public static final String CIDADE = "JUAZEIRO DO NORTE";
    public static final String ESTADO = "CE";
    public static final String REGIAO = "NORDESTE";
    public static final Set<String> ESTADOS = Arrays.asList("CE", "PE", "PI", "RN").stream().collect(Collectors.toSet()) ;

}
