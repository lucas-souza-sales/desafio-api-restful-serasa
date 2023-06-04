package br.com.serasa.api.vendedores.exceptions;

public class VendedorNotFoundException extends RuntimeException {
    
	public VendedorNotFoundException(String message) {
        super(message);
    }
}
